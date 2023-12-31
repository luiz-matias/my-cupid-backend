package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.InterestEntity;
import com.luizmatias.mycupid.domain.entities.Interest;

public class InterestEntityMapper {

    public static Interest toInterest(InterestEntity interestEntity) {
        return new Interest(
                interestEntity.getId(),
                interestEntity.getName(),
                interestEntity.getDescription()
        );
    }

    public static InterestEntity toInterestEntity(Interest interest) {
        return new InterestEntity(
                interest.getId(),
                interest.getName(),
                interest.getDescription()
        );
    }

}
