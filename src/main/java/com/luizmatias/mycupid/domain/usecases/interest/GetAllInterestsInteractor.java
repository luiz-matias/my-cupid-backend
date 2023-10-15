package com.luizmatias.mycupid.domain.usecases.interest;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;

public class GetAllInterestsInteractor {

    private final InterestRepository interestRepository;

    public GetAllInterestsInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public PageResponse<Interest> getInterests(PageRequest pageRequest) {
        return interestRepository.getAllInterests(pageRequest);
    }

}
