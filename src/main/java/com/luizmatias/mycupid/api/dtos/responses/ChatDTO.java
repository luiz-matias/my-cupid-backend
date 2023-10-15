package com.luizmatias.mycupid.api.dtos.responses;

import java.util.Date;

public record ChatDTO(
        Long id,
        UserDTO firstUser,
        UserDTO secondUser,
        Date createdAt
) {
}
