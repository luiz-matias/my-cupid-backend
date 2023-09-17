package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.Optional;

public class GetUserInteractor {

    private final UserRepository userRepository;

    public GetUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) throws ResourceNotFoundException {
        return userRepository.getUser(id);
    }

}
