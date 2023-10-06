package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.PasswordToken;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.PasswordTokenRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.Date;
import java.util.Optional;

public class RequestResetPasswordInteractor {

    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public RequestResetPasswordInteractor(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User requestResetPassword(String token, String password) throws InvalidTokenException, ResourceNotFoundException {
        Optional<PasswordToken> passwordTokenOptional = passwordTokenRepository.getPasswordTokenByToken(token);

        if (passwordTokenOptional.isEmpty()) {
            throw new InvalidTokenException("Invalid password token");
        }

        PasswordToken passwordToken = passwordTokenOptional.get();
        passwordTokenRepository.deletePasswordToken(passwordToken);

        if (passwordToken.getExpiresAt().before(new Date())) {
            throw new InvalidTokenException("Token expired");
        }

        String encodedPassword = passwordEncoder.encode(password);

        return userRepository.updateUserPassword(passwordToken.getUser().getId(), encodedPassword);
    }

}