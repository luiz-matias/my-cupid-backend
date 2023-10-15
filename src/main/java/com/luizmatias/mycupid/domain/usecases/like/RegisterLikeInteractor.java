package com.luizmatias.mycupid.domain.usecases.like;

import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.LikeOnSameUserException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;
import com.luizmatias.mycupid.domain.usecases.match.CreateMatchInteractor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class RegisterLikeInteractor {

    private final UserRepository userRepository;
    private final CreateMatchInteractor createMatchInteractor;

    public RegisterLikeInteractor(UserRepository userRepository, CreateMatchInteractor createMatchInteractor) {
        this.userRepository = userRepository;
        this.createMatchInteractor = createMatchInteractor;
    }

    public Optional<Match> registerLike(User userFrom, Long toId) throws ResourceNotFoundException, LikeOnSameUserException, FailedToSendNotificationException {
        if (userFrom.getId().equals(toId)) {
            throw new LikeOnSameUserException("User can't like himself");
        }

        User userTo = userRepository.getUser(toId);

        Optional<Match> optionalMatch = Optional.empty();
        boolean isMatch = userTo.getLikedUsers().stream().anyMatch(user -> Objects.equals(user.getId(), userFrom.getId()));

        if (isMatch) {
            optionalMatch = Optional.of(
                    createMatchInteractor.createMatch(
                            userFrom,
                            userTo
                    )
            );
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
