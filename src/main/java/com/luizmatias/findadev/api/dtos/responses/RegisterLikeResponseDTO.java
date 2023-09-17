package com.luizmatias.findadev.api.dtos.responses;

public record RegisterLikeResponseDTO(
        boolean isMatch,
        MatchDTO match
) {
}
