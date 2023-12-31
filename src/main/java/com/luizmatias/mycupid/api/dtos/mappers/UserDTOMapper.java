package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.api.dtos.responses.UserDTO;
import com.luizmatias.mycupid.domain.entities.User;

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
                UserRoleMapper.toUserRole(userDTO.userRole()),
                userDTO.latitude(),
                userDTO.longitude(),
                null,
                userDTO.likedUsers().stream().map(UserDTOMapper::toUserWithoutLikesAndMatches).toList(),
                userDTO.likedByUsers().stream().map(UserDTOMapper::toUserWithoutLikesAndMatches).toList(),
                userDTO.matchesAsClient().stream().map(MatchDTOMapper::toMatch).toList(),
                userDTO.matchesAsDeveloper().stream().map(MatchDTOMapper::toMatch).toList(),
                null,
                userDTO.interests().stream().map(InterestDTOMapper::toInterest).toList()
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
                UserRoleMapper.toUserRoleString(user.getUserRole()),
                user.getLatitude(),
                user.getLongitude(),
                user.getLikedUsers().stream().map(UserDTOMapper::toUserDTOWithoutLikesAndMatches).toList(),
                user.getLikedByUsers().stream().map(UserDTOMapper::toUserDTOWithoutLikesAndMatches).toList(),
                user.getMatchesAsClient().stream().map(MatchDTOMapper::toMatchDTO).toList(),
                user.getMatchesAsDeveloper().stream().map(MatchDTOMapper::toMatchDTO).toList(),
                user.getInterests().stream().map(InterestDTOMapper::toInterestDTO).toList()
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
                UserRoleMapper.toUserRole(userDTO.userRole()),
                userDTO.latitude(),
                userDTO.longitude(),
                null,
                null,
                null,
                null,
                null,
                null,
                userDTO.interests().stream().map(InterestDTOMapper::toInterest).toList()
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
                UserRoleMapper.toUserRoleString(user.getUserRole()),
                user.getLatitude(),
                user.getLongitude(),
                null,
                null,
                null,
                null,
                user.getInterests().stream().map(InterestDTOMapper::toInterestDTO).toList()
        );
    }

}

