package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.repositories.RandomGenerator;
import com.luizmatias.findadev.domain.repositories.UserTemporaryTokenRepository;

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

    public UserTemporaryToken createToken(User user) {
        UserTemporaryToken userTemporaryToken = new UserTemporaryToken();
        userTemporaryToken.setUser(user);
        userTemporaryToken.setToken(randomGenerator.generate());
        userTemporaryToken.setExpiresAt(Date.from(getExpirationDate()));

        return userTemporaryTokenRepository.createTemporaryToken(userTemporaryToken);
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }

}
