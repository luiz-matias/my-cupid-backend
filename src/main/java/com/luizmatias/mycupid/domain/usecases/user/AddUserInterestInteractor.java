package com.luizmatias.mycupid.domain.usecases.user;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

public class AddUserInterestInteractor {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    public AddUserInterestInteractor(UserRepository userRepository, InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    public User addUserInterest(User user, Long interestId) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Interest interest = interestRepository.getInterestById(interestId);

        ArrayList<Interest> interests = new ArrayList<>(user.getInterests());

        if (interests.stream().anyMatch(interestItem -> Objects.equals(interestItem.getId(), interestId))) {
            throw new ResourceAlreadyExistsException("user already has this interest");
        }

        interests.add(interest);
        user.setInterests(interests);

        return userRepository.updateUserProfile(user);
    }

}
