package com.luizmatias.mycupid.api.dtos.responses;

import java.util.Date;

public record MatchDTO(
        Long id,
        UserDTO clientUser,
        UserDTO developerUser,
        Date matchedAt,
        Date unmatchedAt
) {
}
