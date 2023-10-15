package com.luizmatias.mycupid.domain.usecases.like;

import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

public class RemoveLikeInteractor {

    private final UserRepository userRepository;

    public RemoveLikeInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void removeLike(User userFrom, Long toId) throws ResourceNotFoundException {
        User userTo = userRepository.getUser(toId);

        ArrayList<User> likes = new ArrayList<>();

        if (userFrom.getLikedUsers() != null) {
            likes.addAll(userFrom
                    .getLikedUsers()
                    .stream()
                    .filter(user -> !Objects.equals(user.getId(), userTo.getId()))
                    .toList()
            );
        }

        userFrom.setLikedUsers(likes);
        userRepository.updateUserLikes(userFrom);
    }

}
