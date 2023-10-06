package com.luizmatias.findadev.api.dtos.requests;

import com.luizmatias.findadev.api.validators.StrongPassword;
import jakarta.validation.constraints.NotNull;

public record RequestResetPasswordDTO(
        @NotNull
        String token,
        @StrongPassword
        String password
) {

}
