package com.luizmatias.mycupid.api.dtos.responses;

public record RegisterLikeResponseDTO(
        boolean isMatch,
        MatchDTO match
) {
}
