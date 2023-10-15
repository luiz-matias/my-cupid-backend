package com.luizmatias.mycupid.api.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record ActivateUserDTO(
        @NotNull
        String token
) {

}
