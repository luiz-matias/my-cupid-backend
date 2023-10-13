package com.luizmatias.findadev.api.dtos.requests;

import com.luizmatias.findadev.domain.entities.Interest;
import jakarta.validation.constraints.NotBlank;

public record RegisterInterestDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {

    public Interest toInterest() {
        return new Interest(
                null,
                name,
                description
        );
    }

}
