package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class ChangeUserPasswordInteractor {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationSenderRepository notificationSenderRepository;

    public ChangeUserPasswordInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, NotificationSenderRepository notificationSenderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationSenderRepository = notificationSenderRepository;
    }

    public void changePassword(User user, String newPassword) throws ResourceNotFoundException, FailedToSendNotificationException {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user = userRepository.updateUserPassword(user.getId(), encodedPassword);
        notificationSenderRepository.sendPasswordChanged(user.getEmail(), user.getFirstName());
    }

}