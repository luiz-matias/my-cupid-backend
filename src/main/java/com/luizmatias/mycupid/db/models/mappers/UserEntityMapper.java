package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.UserEntity;
import com.luizmatias.mycupid.domain.entities.User;

import java.util.Collections;

public class UserEntityMapper {

    public static User toUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBio(),
                userEntity.getBirth(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserType(),
                userEntity.getUserRole(),
                userEntity.getLatitude(),
                userEntity.getLongitude(),
                userEntity.getIsEmailVerified(),
                userEntity.getLikedUserEntities().stream().map(UserEntityMapper::toUserWithoutLikesAndMatches).toList(),
                userEntity.getLikedByUserEntities().stream().map(UserEntityMapper::toUserWithoutLikesAndMatches).toList(),
                userEntity.getMatchesAsClient().stream().map(MatchEntityMapper::toMatch).toList(),
                userEntity.getMatchesAsDeveloper().stream().map(MatchEntityMapper::toMatch).toList(),
                Collections.emptyList(),
                userEntity.getInterestEntities().stream().map(InterestEntityMapper::toInterest).toList()
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.getUserRole(),
                user.getLatitude(),
                user.getLongitude(),
                user.isEmailVerified(),
                user.getLikedUsers().stream().map(UserEntityMapper::toUserEntityWithoutLikesAndMatches).toList(),
                user.getLikedByUsers().stream().map(UserEntityMapper::toUserEntityWithoutLikesAndMatches).toList(),
                user.getMatchesAsClient().stream().map(MatchEntityMapper::toMatchEntity).toList(),
                user.getMatchesAsDeveloper().stream().map(MatchEntityMapper::toMatchEntity).toList(),
                Collections.emptyList(),
                user.getInterests().stream().map(InterestEntityMapper::toInterestEntity).toList()
        );
    }

    public static User toUserWithoutLikesAndMatches(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBio(),
                userEntity.getBirth(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserType(),
                userEntity.getUserRole(),
                userEntity.getLatitude(),
                userEntity.getLongitude(),
                userEntity.getIsEmailVerified(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                userEntity.getInterestEntities().stream().map(InterestEntityMapper::toInterest).toList()
        );
    }

    public static UserEntity toUserEntityWithoutLikesAndMatches(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.getUserRole(),
                user.getLatitude(),
                user.getLongitude(),
                user.isEmailVerified(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                user.getInterests().stream().map(InterestEntityMapper::toInterestEntity).toList()
        );
    }

}

