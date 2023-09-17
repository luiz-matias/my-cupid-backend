package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.domain.entities.UserType;

import java.util.Objects;

public class UserTypeMapper {

    public static UserType toUserType(String userType) {
        if (Objects.equals(userType, USER_TYPE_CLIENT)) {
            return UserType.CLIENT;
        } else {
            return UserType.DEVELOPER;
        }
    }

    public static String toUserTypeString(UserType userType) {
        return switch (userType) {
            case CLIENT -> USER_TYPE_CLIENT;
            case DEVELOPER -> USER_TYPE_DEVELOPER;
        };
    }

    public static final String USER_TYPE_CLIENT = "CLIENT";
    public static final String USER_TYPE_DEVELOPER = "DEVELOPER";

}
