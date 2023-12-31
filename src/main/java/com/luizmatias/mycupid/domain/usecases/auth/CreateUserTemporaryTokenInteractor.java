package com.luizmatias.mycupid.domain.usecases.auth;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;
import com.luizmatias.mycupid.domain.repositories.RandomGenerator;
import com.luizmatias.mycupid.domain.repositories.UserTemporaryTokenRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class CreateUserTemporaryTokenInteractor {

    private final UserTemporaryTokenRepository userTemporaryTokenRepository;
    private final RandomGenerator randomGenerator;

    public CreateUserTemporaryTokenInteractor(UserTemporaryTokenRepository userTemporaryTokenRepository, RandomGenerator randomGenerator) {
        this.userTemporaryTokenRepository = userTemporaryTokenRepository;
        this.randomGenerator = randomGenerator;
    }

    public UserTemporaryToken createToken(User user, TokenType tokenType) {
        UserTemporaryToken userTemporaryToken = new UserTemporaryToken();
        userTemporaryToken.setUser(user);
        userTemporaryToken.setToken(randomGenerator.generate());
        userTemporaryToken.setTokenType(tokenType);
        userTemporaryToken.setExpiresAt(Date.from(getExpirationDate()));

        return userTemporaryTokenRepository.createTemporaryToken(userTemporaryToken);
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }

}
