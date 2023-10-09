package com.luizmatias.findadev.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SendMessageDTO(
        @NotBlank
        @Range(min = 1, max = Integer.MAX_VALUE)
        Long fromUser,
        @NotBlank
        @Size(min = 1, max = 500)
        String message,
        @NotNull
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date sentAt
) {
}
