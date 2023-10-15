package com.luizmatias.mycupid.api.dtos.requests;

import com.luizmatias.mycupid.api.validators.NullOrNotBlank;
import com.luizmatias.mycupid.domain.entities.Interest;

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
