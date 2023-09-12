package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.Optional;

public class GetUserInteractor {

    private final UserRepository userRepository;

    public GetUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Optional<User> getUser(Long id) {
        return userRepository.getUser(id);
    }

}
