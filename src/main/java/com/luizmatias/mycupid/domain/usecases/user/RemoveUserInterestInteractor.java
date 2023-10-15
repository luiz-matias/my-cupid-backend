package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

public class RemoveUserInterestInteractor {

    private final UserRepository userRepository;

    public RemoveUserInterestInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User removeUserInterest(User user, Long interestId) throws ResourceNotFoundException {
        ArrayList<Interest> interests = new ArrayList<>(user.getInterests());

        interests = new ArrayList<>(interests.stream().filter(interestItem -> !Objects.equals(interestItem.getId(), interestId)).toList());
        user.setInterests(interests);

        return userRepository.updateUserProfile(user);
    }

}
