package com.luizmatias.findadev.api.dtos;

import java.util.Date;

public record MatchDTO(
        Long id,
        UserDTO clientUser,
        UserDTO developerUser,
        Date matchedAt,
        Date unmatchedAt
) {
}
