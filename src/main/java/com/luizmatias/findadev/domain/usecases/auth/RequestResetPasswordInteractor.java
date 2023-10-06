package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.InvalidTokenException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class RequestResetPasswordInteractor {

    private final UserRepository userRepository;
    private final VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor;
    private final EmailSenderRepository emailSenderRepository;
    private final PasswordEncoder passwordEncoder;

    public RequestResetPasswordInteractor(UserRepository userRepository, VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor, EmailSenderRepository emailSenderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verifyUserTemporaryTokenInteractor = verifyUserTemporaryTokenInteractor;
        this.emailSenderRepository = emailSenderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User requestResetPassword(String token, String password) throws InvalidTokenException, ResourceNotFoundException, FailedToSendEmailException {
        User user = verifyUserTemporaryTokenInteractor.verifyToken(token);

        String encodedPassword = passwordEncoder.encode(password);
        user = userRepository.updateUserPassword(user.getId(), encodedPassword);

        emailSenderRepository.sendPasswordChangedEmail(user.getEmail(), user.getFirstName());
        return user;
    }

}