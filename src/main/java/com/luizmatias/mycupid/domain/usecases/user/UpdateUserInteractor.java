package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

public class UpdateUserInteractor {

    private final UserRepository userRepository;

    public UpdateUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(User existingUser, User user) throws ResourceNotFoundException {
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

        if (user.getLatitude() != null) {
            existingUser.setLatitude(user.getLatitude());
        }

        if (user.getLongitude() != null) {
            existingUser.setLongitude(user.getLongitude());
        }

        return userRepository.updateUserProfile(existingUser);
    }

}
