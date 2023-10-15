package com.luizmatias.mycupid.domain.entities.pagination;

public record PageRequest(
        Integer page,
        Integer count
) {
}
