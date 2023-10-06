package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.InactiveUserException;

public class VerifyUserInteractor {

    public void verifyUser(User user) throws InactiveUserException {
        if (!user.isEmailVerified()) {
            throw new InactiveUserException("user needs to verify their e-mail before using the application");
        }
    }

}
