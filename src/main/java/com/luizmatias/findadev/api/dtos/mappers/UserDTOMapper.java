package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.UserDTO;
import com.luizmatias.findadev.domain.entities.User;

import java.util.Collections;

public class UserDTOMapper {

    static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.bio(),
                userDTO.birth(),
                userDTO.email(),
                userDTO.password(),
                UserTypeDTOMapper.toUserType(userDTO.userType()),
                AddressDTOMapper.toAddress(userDTO.address()),
                userDTO.likedUsers().stream().map(UserDTOMapper::toUserWithoutLikesAndMatches).toList(),
                userDTO.matchesAsClient().stream().map(MatchDTOMapper::toMatch).toList(),
                userDTO.matchesAsDeveloper().stream().map(MatchDTOMapper::toMatch).toList()
        );
    }

    static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                UserTypeDTOMapper.toUserTypeDTO(user.getUserType()),
                AddressDTOMapper.toAddressDTO(user.getAddress()),
                user.getLikedUsers().stream().map(UserDTOMapper::toUserDTOWithoutLikesAndMatches).toList(),
                user.getMatchesAsClient().stream().map(MatchDTOMapper::toMatchDTO).toList(),
                user.getMatchesAsDeveloper().stream().map(MatchDTOMapper::toMatchDTO).toList()
        );
    }

    static User toUserWithoutLikesAndMatches(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.bio(),
                userDTO.birth(),
                userDTO.email(),
                userDTO.password(),
                UserTypeDTOMapper.toUserType(userDTO.userType()),
                AddressDTOMapper.toAddress(userDTO.address()),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    static UserDTO toUserDTOWithoutLikesAndMatches(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirth(),
                user.getEmail(),
                user.getPassword(),
                UserTypeDTOMapper.toUserTypeDTO(user.getUserType()),
                AddressDTOMapper.toAddressDTO(user.getAddress()),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

}

