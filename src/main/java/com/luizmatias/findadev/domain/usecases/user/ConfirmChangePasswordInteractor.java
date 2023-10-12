package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.usecases.auth.VerifyUserTemporaryTokenInteractor;

public class ConfirmChangePasswordInteractor {

    private final VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor;
    private final ChangeUserPasswordInteractor changeUserPasswordInteractor;

    public ConfirmChangePasswordInteractor(VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor, ChangeUserPasswordInteractor changeUserPasswordInteractor) {
        this.verifyUserTemporaryTokenInteractor = verifyUserTemporaryTokenInteractor;
        this.changeUserPasswordInteractor = changeUserPasswordInteractor;
    }


    public void confirmChangePassword(String token, String newPassword) throws ResourceNotFoundException, FailedToSendNotificationException, InvalidTokenException {
        User user = verifyUserTemporaryTokenInteractor.verifyToken(token, TokenType.CHANGE_PASSWORD);
        changeUserPasswordInteractor.changePassword(user, newPassword);
    }

}
