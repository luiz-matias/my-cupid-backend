package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

public class GetAllUsersInteractor {

    private final UserRepository userRepository;

    public GetAllUsersInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PageResponse<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.getAllUsers(pageRequest);
    }

}
