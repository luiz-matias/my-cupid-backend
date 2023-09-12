package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class CreateUserInteractor {

    private final UserRepository userRepository;

    public CreateUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User createUser(User user) {
        return userRepository.createUser(user);
    }

}
