package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.UserTypeDTO;
import com.luizmatias.findadev.domain.entities.UserType;

import java.util.Objects;

public class UserTypeDTOMapper {

    static UserType toUserType(UserTypeDTO userTypeDTO) {
        if (Objects.equals(userTypeDTO.userType(), UserTypeDTO.CLIENT)) {
            return UserType.CLIENT;
        } else {
            return UserType.DEVELOPER;
        }
    }

    static UserTypeDTO toUserTypeDTO(UserType userType) {
        return switch (userType) {
            case CLIENT -> new UserTypeDTO(UserTypeDTO.CLIENT);
            case DEVELOPER -> new UserTypeDTO(UserTypeDTO.DEVELOPER);
        };
    }

}
