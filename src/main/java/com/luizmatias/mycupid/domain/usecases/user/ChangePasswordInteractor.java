package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.PasswordMismatchException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;
import com.luizmatias.mycupid.domain.usecases.auth.CreateUserTemporaryTokenInteractor;

public class ChangePasswordInteractor {

    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final NotificationSenderRepository notificationSenderRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordInteractor(CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository, PasswordEncoder passwordEncoder) {
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.notificationSenderRepository = notificationSenderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(User user, String oldPassword) throws PasswordMismatchException, FailedToSendNotificationException {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordMismatchException("incorrect password");
        }

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user, TokenType.CHANGE_PASSWORD);

        notificationSenderRepository.sendChangePassword(
                userTemporaryToken.getToken(),
                user.getEmail(),
                user.getFirstName()
        );
    }

}
