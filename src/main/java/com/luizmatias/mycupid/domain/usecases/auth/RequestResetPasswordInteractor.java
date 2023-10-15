package com.luizmatias.mycupid.domain.usecases.auth;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.InvalidTokenException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

public class RequestResetPasswordInteractor {

    private final UserRepository userRepository;
    private final VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor;
    private final NotificationSenderRepository notificationSenderRepository;
    private final PasswordEncoder passwordEncoder;

    public RequestResetPasswordInteractor(UserRepository userRepository, VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verifyUserTemporaryTokenInteractor = verifyUserTemporaryTokenInteractor;
        this.notificationSenderRepository = notificationSenderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User requestResetPassword(String token, String password) throws InvalidTokenException, ResourceNotFoundException, FailedToSendNotificationException {
        User user = verifyUserTemporaryTokenInteractor.verifyToken(token, TokenType.RECOVER_PASSWORD);

        String encodedPassword = passwordEncoder.encode(password);
        user = userRepository.updateUserPassword(user.getId(), encodedPassword);

        notificationSenderRepository.sendPasswordChanged(user.getEmail(), user.getFirstName());
        return user;
    }

}