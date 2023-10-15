package com.luizmatias.mycupid.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordDTO(
        @NotNull
        @Email
        String email
) {

}
