package com.luizmatias.findadev.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
        @NotNull
        @Email
        String email,
        @NotNull
        String password
) {

}
