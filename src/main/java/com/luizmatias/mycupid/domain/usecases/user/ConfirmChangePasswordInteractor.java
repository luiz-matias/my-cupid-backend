package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.InvalidTokenException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.usecases.auth.VerifyUserTemporaryTokenInteractor;

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
