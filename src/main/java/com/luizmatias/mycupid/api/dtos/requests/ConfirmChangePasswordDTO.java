package com.luizmatias.mycupid.api.dtos.requests;

import com.luizmatias.mycupid.api.validators.StrongPassword;
import jakarta.validation.constraints.NotNull;

public record ConfirmChangePasswordDTO(
        @NotNull
        String token,
        @StrongPassword
        String newPassword
) {

}
