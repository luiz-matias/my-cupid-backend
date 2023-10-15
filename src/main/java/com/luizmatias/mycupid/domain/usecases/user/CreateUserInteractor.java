package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.TokenType;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;
import com.luizmatias.mycupid.domain.repositories.UserRepository;
import com.luizmatias.mycupid.domain.usecases.auth.CreateUserTemporaryTokenInteractor;

public class CreateUserInteractor {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final NotificationSenderRepository notificationSenderRepository;

    public CreateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.notificationSenderRepository = notificationSenderRepository;
    }


    public User createUser(User user) throws ResourceAlreadyExistsException, FailedToSendNotificationException {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("User with its email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.createUser(user);

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user, TokenType.ACTIVATE_ACCOUNT);

        notificationSenderRepository.sendVerifyAccount(userTemporaryToken.getToken(), user.getEmail(), user.getFirstName());

        return user;
    }

}
