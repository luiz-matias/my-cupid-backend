package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.responses.UserDTO;
import com.luizmatias.findadev.domain.entities.User;

import java.util.Collections;

public class UserDTOMapper {

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.bio(),
                userDTO.birth(),
                userDTO.email(),
                userDTO.password(),
                UserTypeMapper.toUserType(userDTO.userType()),
                UserRoleMapper.toUserRole(userDTO.userRole()),
                userDTO.latitude(),
                userDTO.longitude(),
                userDTO.likedUsers().stream().map(UserDTOMapper::toUserWithoutLikesAndMatches).toList(),
                userDTO.likedByUsers().stream().map(UserDTOMapper::toUserWithoutLikesAndMatches).toList(),
                userDTO.matchesAsClient().stream().map(MatchDTOMapper::toMatch).toList(),
                userDTO.matchesAsDeveloper().stream().map(MatchDTOMapper::toMatch).toList(),
                Collections.emptyList()
        );
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                null,
                UserTypeMapper.toUserTypeString(user.getUserType()),
                UserRoleMapper.toUserRoleString(user.getUserRole()),
                user.getLatitude(),
                user.getLongitude(),
                user.getLikedUsers().stream().map(UserDTOMapper::toUserDTOWithoutLikesAndMatches).toList(),
                user.getLikedByUsers().stream().map(UserDTOMapper::toUserDTOWithoutLikesAndMatches).toList(),
                user.getMatchesAsClient().stream().map(MatchDTOMapper::toMatchDTO).toList(),
                user.getMatchesAsDeveloper().stream().map(MatchDTOMapper::toMatchDTO).toList()
        );
    }

    public static User toUserWithoutLikesAndMatches(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.bio(),
                userDTO.birth(),
                userDTO.email(),
                userDTO.password(),
                UserTypeMapper.toUserType(userDTO.userType()),
                UserRoleMapper.toUserRole(userDTO.userRole()),
                userDTO.latitude(),
                userDTO.longitude(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public static UserDTO toUserDTOWithoutLikesAndMatches(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                null,
                UserTypeMapper.toUserTypeString(user.getUserType()),
                UserRoleMapper.toUserRoleString(user.getUserRole()),
                user.getLatitude(),
                user.getLongitude(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

}

