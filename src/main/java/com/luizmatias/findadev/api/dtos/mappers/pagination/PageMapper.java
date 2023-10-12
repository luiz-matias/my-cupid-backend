package com.luizmatias.findadev.api.dtos.mappers.pagination;

import com.luizmatias.findadev.api.dtos.mappers.OrderTypeMapper;
import com.luizmatias.findadev.domain.entities.pagination.OrderType;
import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

public class PageMapper {

    public static PageRequest toPageRequest(PageRequestDTO pageRequestDTO) {
        return new PageRequest(
                pageRequestDTO.page - 1,
                pageRequestDTO.getCount(),
                OrderTypeMapper.toOrderType(pageRequestDTO.getOrder()),
                pageRequestDTO.getSort()
        );
    }

    public static org.springframework.data.domain.PageRequest toJpaPageRequest(PageRequest pageRequest) {
        Sort sort = Sort.unsorted();
        if (pageRequest.sort() != null && !pageRequest.sort().isEmpty()) {
            sort = pageRequest.order() == OrderType.ASC ? Sort.by(Sort.Direction.ASC, pageRequest.sort().toArray(new String[0])) : Sort.by(Sort.Direction.DESC, pageRequest.sort().toArray(new String[0]));
        }
        return org.springframework.data.domain.PageRequest.of(
                pageRequest.page(),
                pageRequest.count(),
                sort
        );
    }

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(
                page.getNumber() + 1,
                page.stream().count(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent()
        );
    }

    public static <T, R> PageResponseDTO<R> toPageResponseDTO(PageResponse<T> pageResponse, Function<T, R> mapper) {
        return new PageResponseDTO<R>(
                pageResponse.getPage(),
                pageResponse.getCount(),
                pageResponse.getTotalPages(),
                pageResponse.getTotalCount(),
                pageResponse.getContent().stream().map(mapper).toList()
        );
    }

}
