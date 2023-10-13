package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.auth.CreateUserTemporaryTokenInteractor;

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
