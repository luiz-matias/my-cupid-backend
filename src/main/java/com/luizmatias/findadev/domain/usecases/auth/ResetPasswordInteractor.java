package com.luizmatias.findadev.domain.usecases.auth;

import com.luizmatias.findadev.domain.entities.PasswordToken;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordTokenRepository;
import com.luizmatias.findadev.domain.repositories.RandomGenerator;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

public class ResetPasswordInteractor {

    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final EmailSenderRepository emailSenderRepository;
    private final RandomGenerator randomGenerator;

    public ResetPasswordInteractor(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, EmailSenderRepository emailSenderRepository, RandomGenerator randomGenerator) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.emailSenderRepository = emailSenderRepository;
        this.randomGenerator = randomGenerator;
    }

    public void resetPassword(String email) throws ResourceNotFoundException, FailedToSendEmailException {
        Optional<User> userOptional = userRepository.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = userOptional.get();

        PasswordToken passwordToken = new PasswordToken();
        passwordToken.setUser(user);
        passwordToken.setToken(randomGenerator.generate());
        passwordToken.setExpiresAt(Date.from(getExpirationDate()));

        passwordToken = passwordTokenRepository.createPasswordToken(passwordToken);

        emailSenderRepository.sendPasswordRecoveryEmail(
                passwordToken.getToken(),
                userOptional.get().getEmail()
        );
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
