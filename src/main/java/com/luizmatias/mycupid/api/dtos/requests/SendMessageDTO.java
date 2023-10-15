package com.luizmatias.mycupid.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SendMessageDTO(
        @NotNull
        @Size(min = 1, max = 500)
        String message,
        @NotNull
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date sentAt
) {
}
