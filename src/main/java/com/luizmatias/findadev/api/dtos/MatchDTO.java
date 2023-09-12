package com.luizmatias.findadev.api.dtos;

import java.time.LocalDateTime;

public record MatchDTO(
        Long id,
        UserDTO clientUser,
        UserDTO developerUser,
        LocalDateTime matchedAt,
        LocalDateTime unmatchedAt
) {
}
