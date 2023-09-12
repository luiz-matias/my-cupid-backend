package com.luizmatias.findadev.api.dtos;

public record UserTypeDTO(
        String userType
) {
    public static final String CLIENT = "CLIENT";
    public static final String DEVELOPER = "DEVELOPER";
}
