package com.luizmatias.mycupid.api.dtos.requests;

import com.luizmatias.mycupid.api.validators.NullOrNotBlank;
import com.luizmatias.mycupid.domain.entities.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
                null,
                null,
                null,
                null,
                latitude,
                longitude,
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
