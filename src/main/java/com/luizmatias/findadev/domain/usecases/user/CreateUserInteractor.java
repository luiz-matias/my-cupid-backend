package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class CreateUserInteractor {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User user) throws ResourceAlreadyExistsException {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("User with its email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.createUser(user);
    }

}
