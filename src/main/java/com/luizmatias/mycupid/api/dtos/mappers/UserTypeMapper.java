package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.domain.entities.UserType;

import java.util.Objects;

public class UserTypeMapper {

    public static UserType toUserType(String userType) {
        if (Objects.equals(userType, UserType.CLIENT.getType())) {
            return UserType.CLIENT;
        } else {
            return UserType.DEVELOPER;
        }
    }

    public static String toUserTypeString(UserType userType) {
        return userType.getType();
    }

}
