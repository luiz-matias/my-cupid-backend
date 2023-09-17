package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class UpdateUserInteractor {

    private final UserRepository userRepository;

    public UpdateUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        User existingUser = userRepository.getUser(id);

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getBio() != null) {
            existingUser.setBio(user.getBio());
        }

        if (user.getBirth() != null) {
            existingUser.setBirth(user.getBirth());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getLatitude() != null) {
            existingUser.setLatitude(user.getLatitude());
        }

        if (user.getLongitude() != null) {
            existingUser.setLongitude(user.getLongitude());
        }

        return userRepository.updateUserProfile(existingUser);
    }

}
