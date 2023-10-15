package com.luizmatias.mycupid.api.dtos.mappers.pagination;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class PageRequestDTO {

    @Min(1)
    Integer page;
    @Range(min = 1, max = 100)
    Integer count;

    @ConstructorProperties({"page", "count"})
    public PageRequestDTO(Integer page, Integer count) {
        this.page = page != null ? page : 1;
        this.count = count != null ? count : 10;
    }

}
