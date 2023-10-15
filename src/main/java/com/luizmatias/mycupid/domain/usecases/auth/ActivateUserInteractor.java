package com.luizmatias.mycupid.domain.usecases.auth;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.InvalidTokenException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

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
