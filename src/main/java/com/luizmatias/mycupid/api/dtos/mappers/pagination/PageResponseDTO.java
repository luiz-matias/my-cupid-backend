package com.luizmatias.mycupid.api.dtos.mappers.pagination;

import java.util.List;

public record PageResponseDTO<T>(
        int page,
        long count,
        long totalPages,
        long totalCount,
        List<T> content
) {
}
