package com.luizmatias.findadev.domain.entities.pagination;

import java.util.List;

public record PageRequest(
        Integer page,
        Integer count,
        OrderType order,
        List<String> sort
) {
}
