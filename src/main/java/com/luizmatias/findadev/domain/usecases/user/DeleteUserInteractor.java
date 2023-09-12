package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class DeleteUserInteractor {

    private final UserRepository userRepository;

    public DeleteUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
