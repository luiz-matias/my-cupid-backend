package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.PasswordMismatchException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.usecases.auth.CreateUserTemporaryTokenInteractor;

public class ChangePasswordInteractor {

    private final CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor;
    private final EmailSenderRepository emailSenderRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordInteractor(CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, EmailSenderRepository emailSenderRepository, PasswordEncoder passwordEncoder) {
        this.createUserTemporaryTokenInteractor = createUserTemporaryTokenInteractor;
        this.emailSenderRepository = emailSenderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(User user, String oldPassword) throws PasswordMismatchException, FailedToSendEmailException {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordMismatchException("incorrect password");
        }

        UserTemporaryToken userTemporaryToken = createUserTemporaryTokenInteractor.createToken(user, TokenType.CHANGE_PASSWORD);

        emailSenderRepository.sendChangePasswordEmail(
                userTemporaryToken.getToken(),
                user.getEmail(),
                user.getFirstName()
        );
    }

}
