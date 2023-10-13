package com.luizmatias.findadev.api.dtos.requests;

import com.luizmatias.findadev.api.validators.NullOrNotBlank;
import com.luizmatias.findadev.domain.entities.Interest;
import jakarta.validation.constraints.NotBlank;

public record UpdateInterestDTO(
        @NullOrNotBlank
        String name,
        @NullOrNotBlank
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
