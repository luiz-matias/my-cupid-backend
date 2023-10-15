package com.luizmatias.mycupid.api.controllers.auth;

import com.luizmatias.mycupid.api.controllers.user.UserController;
import com.luizmatias.mycupid.api.dtos.mappers.UserDTOMapper;
import com.luizmatias.mycupid.api.dtos.requests.*;
import com.luizmatias.mycupid.api.dtos.responses.TokenResponseDTO;
import com.luizmatias.mycupid.api.dtos.responses.UserDTO;
import com.luizmatias.mycupid.api.security.TokenService;
import com.luizmatias.mycupid.db.models.UserEntity;
import com.luizmatias.mycupid.db.models.mappers.UserEntityMapper;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.*;
import com.luizmatias.mycupid.domain.usecases.auth.ActivateUserInteractor;
import com.luizmatias.mycupid.domain.usecases.auth.RequestResetPasswordInteractor;
import com.luizmatias.mycupid.domain.usecases.auth.ResetPasswordInteractor;
import com.luizmatias.mycupid.domain.usecases.auth.VerifyUserInteractor;
import com.luizmatias.mycupid.domain.usecases.user.CreateUserInteractor;
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
    private final ActivateUserInteractor activateUserInteractor;
    private final VerifyUserInteractor verifyUserInteractor;
    private final ResetPasswordInteractor resetPasswordInteractor;
    private final RequestResetPasswordInteractor requestResetPasswordInteractor;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, CreateUserInteractor createUserInteractor, ActivateUserInteractor activateUserInteractor, VerifyUserInteractor verifyUserInteractor, ResetPasswordInteractor resetPasswordInteractor, RequestResetPasswordInteractor requestResetPasswordInteractor) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.createUserInteractor = createUserInteractor;
        this.activateUserInteractor = activateUserInteractor;
        this.verifyUserInteractor = verifyUserInteractor;
        this.resetPasswordInteractor = resetPasswordInteractor;
        this.requestResetPasswordInteractor = requestResetPasswordInteractor;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody @Valid LoginUserDTO loginUserDTO) throws LoginFailedException, InactiveUserException {
        try {
            Authentication usernamePassword = new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.password());
            Authentication authentication = authenticationManager.authenticate(usernamePassword);

            User user = UserEntityMapper.toUser((UserEntity) authentication.getPrincipal());

            verifyUserInteractor.verifyUser(user);

            String token = tokenService.generateToken(user.getEmail());

            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (AuthenticationException e) {
            throw new LoginFailedException("unable to login with given email and password");
        }
    }

    @Transactional
    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO) throws ResourceAlreadyExistsException, FailedToSendNotificationException {
        User user = registerUserDTO.toUser();
        user = createUserInteractor.createUser(user);
        URI uri = UriComponentsBuilder.newInstance().path("{path}/{id}").buildAndExpand(UserController.USERS_PATH, user.getId()).toUri();
        return ResponseEntity.created(uri).body(UserDTOMapper.toUserDTO(user));
    }

    @Transactional
    @PostMapping(path = "/activate")
    public ResponseEntity<UserDTO> activateUser(@RequestBody @Valid ActivateUserDTO activateUserDTO) throws InvalidTokenException, ResourceNotFoundException {
        User user = activateUserInteractor.activateUser(activateUserDTO.token());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

    @PostMapping(path = "/reset-password")
    public ResponseEntity<TokenResponseDTO> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) throws FailedToSendNotificationException {
        try {
            resetPasswordInteractor.resetPassword(resetPasswordDTO.email());
        } catch (ResourceNotFoundException e) {
            System.out.println("User not found, skipping password reset.");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/request-reset-password")
    public ResponseEntity<UserDTO> requestResetPassword(@RequestBody @Valid RequestResetPasswordDTO requestResetPasswordDTO) throws InvalidTokenException, ResourceNotFoundException, FailedToSendNotificationException {
        User user = requestResetPasswordInteractor.requestResetPassword(requestResetPasswordDTO.token(), requestResetPasswordDTO.password());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

}