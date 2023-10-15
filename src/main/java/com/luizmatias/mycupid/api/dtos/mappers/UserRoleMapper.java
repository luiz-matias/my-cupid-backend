package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.domain.entities.UserRole;

import java.util.Objects;

public class UserRoleMapper {

    public static UserRole toUserRole(String userRole) {
        if (Objects.equals(userRole, UserRole.ADMIN.getRole())) {
            return UserRole.ADMIN;
        } else {
            return UserRole.USER;
        }
    }

    public static String toUserRoleString(UserRole userRole) {
        return userRole.getRole();
    }

}
