package com.luizmatias.findadev.api.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record ChangePasswordDTO(
        @NotNull
        String oldPassword
) {

}
