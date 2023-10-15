package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

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