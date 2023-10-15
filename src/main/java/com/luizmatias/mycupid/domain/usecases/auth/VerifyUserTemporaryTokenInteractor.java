package com.luizmatias.mycupid.domain.usecases.auth;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;
import com.luizmatias.mycupid.domain.exceptions.InvalidTokenException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserTemporaryTokenRepository;

import java.util.Date;
import java.util.Optional;

public class VerifyUserTemporaryTokenInteractor {

    private final UserTemporaryTokenRepository userTemporaryTokenRepository;

    public VerifyUserTemporaryTokenInteractor(UserTemporaryTokenRepository userTemporaryTokenRepository) {
        this.userTemporaryTokenRepository = userTemporaryTokenRepository;
    }

    public User verifyToken(String token, TokenType tokenType) throws InvalidTokenException, ResourceNotFoundException {
        Optional<UserTemporaryToken> userTemporaryTokenOptional = userTemporaryTokenRepository.getTemporaryTokenByTokenAndType(token, tokenType);

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
