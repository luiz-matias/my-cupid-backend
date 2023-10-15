package com.luizmatias.mycupid.api.dtos.requests;

import com.luizmatias.mycupid.api.validators.StrongPassword;
import jakarta.validation.constraints.NotNull;

public record RequestResetPasswordDTO(
        @NotNull
        String token,
        @StrongPassword
        String password
) {

}
