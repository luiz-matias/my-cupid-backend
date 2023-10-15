package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

public class GetAllUsersInteractor {

    private final UserRepository userRepository;

    public GetAllUsersInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PageResponse<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.getAllUsers(pageRequest);
    }

}
