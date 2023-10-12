package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;

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