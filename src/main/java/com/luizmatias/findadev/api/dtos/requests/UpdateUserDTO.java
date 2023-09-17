package com.luizmatias.findadev.api.dtos.requests;

import com.luizmatias.findadev.api.validators.NullOrNotBlank;
import com.luizmatias.findadev.domain.entities.User;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collections;
import java.util.Date;

public record UpdateUserDTO(
        @NullOrNotBlank
        @Size(min = 2, max = 50)
        String firstName,
        @NullOrNotBlank
        @Size(min = 2, max = 50)
        String lastName,
        @NullOrNotBlank
        @Size(min = 1, max = 500)
        String bio,
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date birth,
        @NullOrNotBlank
        @Email
        String email,
        @Min(-90)
        @Max(90)
        Double latitude,
        @Min(-180)
        @Max(180)
        Double longitude
) {
    public User toUser() {
        return new User(
                null,
                firstName,
                lastName,
                bio,
                birth,
                email,
                null,
                null,
                latitude,
                longitude,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
