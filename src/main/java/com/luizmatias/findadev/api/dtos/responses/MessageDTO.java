package com.luizmatias.findadev.api.dtos.responses;

import java.util.Date;

public record MessageDTO(
        Long id,
        Long chatId,
        Long fromUser,
        String message,
        Date sentAt
) {
}
