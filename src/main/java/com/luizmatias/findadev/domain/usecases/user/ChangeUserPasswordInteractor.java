package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class ChangeUserPasswordInteractor {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderRepository emailSenderRepository;

    public ChangeUserPasswordInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSenderRepository emailSenderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderRepository = emailSenderRepository;
    }

    public void changePassword(User user, String newPassword) throws ResourceNotFoundException, FailedToSendEmailException {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user = userRepository.updateUserPassword(user.getId(), encodedPassword);
        emailSenderRepository.sendPasswordChangedEmail(user.getEmail(), user.getFirstName());
    }

}