package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.api.dtos.responses.InterestDTO;
import com.luizmatias.mycupid.domain.entities.Interest;

public class InterestDTOMapper {

    public static Interest toInterest(InterestDTO interestDTO) {
        return new Interest(
                interestDTO.id(),
                interestDTO.name(),
                interestDTO.description()
        );
    }

    public static InterestDTO toInterestDTO(Interest interest) {
        return new InterestDTO(
                interest.getId(),
                interest.getName(),
                interest.getDescription()
        );
    }

}
