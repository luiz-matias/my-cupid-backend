package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class CreateUserInteractor {

    private final UserRepository userRepository;

    public CreateUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws ResourceAlreadyExistsException {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("User with its email already exists");
        }

        return userRepository.createUser(user);
    }

}
