package com.luizmatias.findadev.api.dtos.requests;

import com.luizmatias.findadev.api.dtos.mappers.UserRoleMapper;
import com.luizmatias.findadev.api.dtos.mappers.UserTypeMapper;
import com.luizmatias.findadev.api.validators.StrongPassword;
import com.luizmatias.findadev.api.validators.UserRole;
import com.luizmatias.findadev.api.validators.UserType;
import com.luizmatias.findadev.domain.entities.User;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collections;
import java.util.Date;

public record RegisterUserDTO(
        @NotBlank
        @Size(min = 2, max = 50)
        String firstName,
        @NotBlank
        @Size(min = 2, max = 50)
        String lastName,
        @NotBlank
        @Size(min = 1, max = 500)
        String bio,
        @NotNull
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date birth,
        @NotBlank
        @Email
        String email,
        @StrongPassword
        String password,
        @NotNull
        @UserType
        String userType,
        @NotNull
        @UserRole
        String userRole,
        @NotNull
        @Min(-90)
        @Max(90)
        Double latitude,
        @NotNull
        @Min(-180)
        @Max(180)
        Double longitude
) {
    public User toUser() {
        return new User(
                null,
                firstName,
                lastName,
                bio,
                birth,
                email,
                password,
                UserTypeMapper.toUserType(userType),
                UserRoleMapper.toUserRole(userRole),
                latitude,
                longitude,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
