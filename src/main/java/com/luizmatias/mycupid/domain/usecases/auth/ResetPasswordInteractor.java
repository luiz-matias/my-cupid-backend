package com.luizmatias.mycupid.domain.usecases.auth;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

import java.util.Optional;

public class ResetPasswordInteractor {

    private final UserRepository userRepository;
    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final NotificationSenderRepository notificationSenderRepository;

    public ResetPasswordInteractor(UserRepository userRepository, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository) {
        this.userRepository = userRepository;
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.notificationSenderRepository = notificationSenderRepository;
    }

    public void resetPassword(String email) throws ResourceNotFoundException, FailedToSendNotificationException {
        Optional<User> userOptional = userRepository.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = userOptional.get();

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user, TokenType.RECOVER_PASSWORD);

        notificationSenderRepository.sendPasswordRecovery(
                userTemporaryToken.getToken(),
                user.getEmail(),
                user.getFirstName()
        );
    }

}
