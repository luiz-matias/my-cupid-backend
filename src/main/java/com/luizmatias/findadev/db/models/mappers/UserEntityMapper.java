package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.domain.entities.User;

import java.util.Collections;

public class UserEntityMapper {

    static User toUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBio(),
                userEntity.getBirth(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserType(),
                AddressEntityMapper.toAddress(userEntity.getAddressEntity()),
                userEntity.getLikedUserEntities().stream().map(UserEntityMapper::toUserWithoutLikesAndMatches).toList(),
                userEntity.getMatchesAsClient().stream().map(MatchEntityMapper::toMatch).toList(),
                userEntity.getMatchesAsDeveloper().stream().map(MatchEntityMapper::toMatch).toList()
        );
    }

    static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                AddressEntityMapper.toAddressEntity(user.getAddress()),
                user.getLikedUsers().stream().map(UserEntityMapper::toUserEntityWithoutLikesAndMatches).toList(),
                user.getMatchesAsClient().stream().map(MatchEntityMapper::toMatchEntity).toList(),
                user.getMatchesAsDeveloper().stream().map(MatchEntityMapper::toMatchEntity).toList()
        );
    }

    static User toUserWithoutLikesAndMatches(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBio(),
                userEntity.getBirth(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserType(),
                AddressEntityMapper.toAddress(userEntity.getAddressEntity()),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    static UserEntity toUserEntityWithoutLikesAndMatches(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                AddressEntityMapper.toAddressEntity(user.getAddress()),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

}

