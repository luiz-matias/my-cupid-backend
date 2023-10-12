package com.luizmatias.findadev.api.dtos.mappers.pagination;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageRequestDTO {

    @Min(1)
    Integer page;
    @Range(min = 1, max = 100)
    Integer count;
    @com.luizmatias.findadev.api.validators.OrderType
    String order;
    List<String> sort;

    @ConstructorProperties({"page", "count", "order", "sort"})
    public PageRequestDTO(Integer page, Integer count, String order, List<String> sort) {
        this.page = page != null ? page : 1;
        this.count = count != null ? count : 10;
        this.order = order != null ? order : "asc";
        this.sort = sort != null ? sort : Collections.emptyList();
    }

}
