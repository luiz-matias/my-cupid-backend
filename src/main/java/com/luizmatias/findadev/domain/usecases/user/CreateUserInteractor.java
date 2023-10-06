package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.auth.CreateUserTemporaryTokenInteractor;

public class CreateUserInteractor {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final EmailSenderRepository emailSenderRepository;

    public CreateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, EmailSenderRepository emailSenderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.emailSenderRepository = emailSenderRepository;
    }


    public User createUser(User user) throws ResourceAlreadyExistsException, FailedToSendEmailException {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("User with its email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.createUser(user);

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user);

        emailSenderRepository.sendVerifyAccountEmail(userTemporaryToken.getToken(), user.getEmail(), user.getFirstName());

        return user;
    }

}
