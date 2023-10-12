package com.luizmatias.findadev.domain.entities.pagination;

public record PageRequest(
        Integer page,
        Integer count
) {
}
