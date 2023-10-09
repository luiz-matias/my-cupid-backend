package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.auth.VerifyUserTemporaryTokenInteractor;

public class ActivateUserInteractor {

    private final UserRepository userRepository;
    private final VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor;

    public ActivateUserInteractor(UserRepository userRepository, VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor) {
        this.userRepository = userRepository;
        this.verifyUserTemporaryTokenInteractor = verifyUserTemporaryTokenInteractor;
    }

    public User activateUser(String token) throws InvalidTokenException, ResourceNotFoundException {
        User user = verifyUserTemporaryTokenInteractor.verifyToken(token, TokenType.ACTIVATE_ACCOUNT);
        user = userRepository.verifyUserEmail(user);
        return user;
    }

}
