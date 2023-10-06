package com.luizmatias.findadev.api.controllers.auth;

import com.luizmatias.findadev.api.controllers.user.UserController;
import com.luizmatias.findadev.api.dtos.mappers.UserDTOMapper;
import com.luizmatias.findadev.api.dtos.requests.LoginUserDTO;
import com.luizmatias.findadev.api.dtos.requests.RegisterUserDTO;
import com.luizmatias.findadev.api.dtos.requests.RequestResetPasswordDTO;
import com.luizmatias.findadev.api.dtos.requests.ResetPasswordDTO;
import com.luizmatias.findadev.api.dtos.responses.TokenResponseDTO;
import com.luizmatias.findadev.api.dtos.responses.UserDTO;
import com.luizmatias.findadev.api.security.TokenService;
import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.*;
import com.luizmatias.findadev.domain.usecases.user.CreateUserInteractor;
import com.luizmatias.findadev.domain.usecases.auth.RequestResetPasswordInteractor;
import com.luizmatias.findadev.domain.usecases.auth.ResetPasswordInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(AuthController.AUTH_PATH)
public class AuthController {

    public static final String AUTH_PATH = "/auth";

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final CreateUserInteractor createUserInteractor;
    private final ResetPasswordInteractor resetPasswordInteractor;
    private final RequestResetPasswordInteractor requestResetPasswordInteractor;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, CreateUserInteractor createUserInteractor, ResetPasswordInteractor resetPasswordInteractor, RequestResetPasswordInteractor requestResetPasswordInteractor) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.createUserInteractor = createUserInteractor;
        this.resetPasswordInteractor = resetPasswordInteractor;
        this.requestResetPasswordInteractor = requestResetPasswordInteractor;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody @Valid LoginUserDTO loginUserDTO) throws LoginFailedException {
        try {
            Authentication usernamePassword = new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.password());
            Authentication authentication = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (AuthenticationException e) {
            throw new LoginFailedException("unable to login with given email and password");
        }
    }

    @Transactional
    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO) throws ResourceAlreadyExistsException {
        User user = registerUserDTO.toUser();
        user = createUserInteractor.createUser(user);
        URI uri = UriComponentsBuilder.newInstance().path("{path}/{id}").buildAndExpand(UserController.USERS_PATH, user.getId()).toUri();
        return ResponseEntity.created(uri).body(UserDTOMapper.toUserDTO(user));
    }

    @PostMapping(path = "/reset-password")
    public ResponseEntity<TokenResponseDTO> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) throws FailedToSendEmailException {
        try {
            resetPasswordInteractor.resetPassword(resetPasswordDTO.email());
        } catch (ResourceNotFoundException e) {
            System.out.println("User not found, skipping password reset.");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/request-reset-password")
    public ResponseEntity<UserDTO> requestResetPassword(@RequestBody @Valid RequestResetPasswordDTO requestResetPasswordDTO) throws InvalidTokenException, ResourceNotFoundException {
        User user = requestResetPasswordInteractor.requestResetPassword(requestResetPasswordDTO.token(), requestResetPasswordDTO.password());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

}