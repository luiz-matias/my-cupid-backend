package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.Optional;

public class ResetPasswordInteractor {

    private final UserRepository userRepository;
    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final EmailSenderRepository emailSenderRepository;

    public ResetPasswordInteractor(UserRepository userRepository, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, EmailSenderRepository emailSenderRepository) {
        this.userRepository = userRepository;
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.emailSenderRepository = emailSenderRepository;
    }

    public void resetPassword(String email) throws ResourceNotFoundException, FailedToSendEmailException {
        Optional<User> userOptional = userRepository.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = userOptional.get();

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user);

        emailSenderRepository.sendPasswordRecoveryEmail(
                userTemporaryToken.getToken(),
                user.getEmail(),
                user.getFirstName()
        );
    }

}
