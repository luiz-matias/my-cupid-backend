package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.List;

public class GetAllUsersInteractor {

    private final UserRepository userRepository;

    public GetAllUsersInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
