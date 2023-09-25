package com.luizmatias.findadev.domain.usecases.like;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.entities.UserType;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserException;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserTypeException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.match.CreateMatchInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class RegisterLikeInteractor {

    private final UserRepository userRepository;
    private final CreateMatchInteractor createMatchInteractor;

    public RegisterLikeInteractor(UserRepository userRepository, CreateMatchInteractor createMatchInteractor) {
        this.userRepository = userRepository;
        this.createMatchInteractor = createMatchInteractor;
    }

    public Optional<Match> registerLike(Long fromId, Long toId) throws ResourceNotFoundException, LikeOnSameUserException, LikeOnSameUserTypeException {
        if (fromId.equals(toId)) {
            throw new LikeOnSameUserException("User can't like himself");
        }

        User userFrom = userRepository.getUser(fromId);
        User userTo = userRepository.getUser(toId);

        if (userFrom.getUserType() == userTo.getUserType()) {
            throw new LikeOnSameUserTypeException("User can't like another user with same user type");
        }

        Optional<Match> optionalMatch = Optional.empty();
        boolean isMatch = userTo.getLikedUsers().stream().anyMatch(user -> Objects.equals(user.getId(), fromId));

        if (isMatch) {
            User clientUser = userFrom.getUserType() == UserType.CLIENT ? userFrom : userTo;
            User developerUser = userFrom.getUserType() == UserType.DEVELOPER ? userFrom : userTo;

            optionalMatch = Optional.of(createMatchInteractor.createMatch(new Match(
                    null,
                    clientUser,
                    developerUser,
                    new Date(),
                    null
            )));
        } else {
            ArrayList<User> likes = new ArrayList<>();

            if (userFrom.getLikedUsers() != null) {
                likes.addAll(userFrom.getLikedUsers());
            }

            likes.add(userTo);
            userFrom.setLikedUsers(likes);
            userRepository.updateUserLikes(userFrom);
        }

        return optionalMatch;
    }

}