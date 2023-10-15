package com.luizmatias.mycupid.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record UserInterestDTO(
        @NotNull
        @Range(min = 1)
        Long interestId
) {

}
