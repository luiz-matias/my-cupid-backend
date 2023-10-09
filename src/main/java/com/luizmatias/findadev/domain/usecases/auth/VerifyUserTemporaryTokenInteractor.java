package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserTemporaryTokenRepository;

import java.util.Date;
import java.util.Optional;

public class VerifyUserTemporaryTokenInteractor {

    private final UserTemporaryTokenRepository userTemporaryTokenRepository;

    public VerifyUserTemporaryTokenInteractor(UserTemporaryTokenRepository userTemporaryTokenRepository) {
        this.userTemporaryTokenRepository = userTemporaryTokenRepository;
    }

    public User verifyToken(String token) throws InvalidTokenException, ResourceNotFoundException {
        Optional<UserTemporaryToken> userTemporaryTokenOptional = userTemporaryTokenRepository.getTemporaryTokenByToken(token);

        if (userTemporaryTokenOptional.isEmpty()) {
            throw new InvalidTokenException("Invalid token");
        }

        UserTemporaryToken userTemporaryToken = userTemporaryTokenOptional.get();
        userTemporaryTokenRepository.deleteTemporaryToken(userTemporaryToken);

        if (userTemporaryToken.getExpiresAt().before(new Date())) {
            throw new InvalidTokenException("Token expired");
        }
        return userTemporaryToken.getUser();
    }

}
