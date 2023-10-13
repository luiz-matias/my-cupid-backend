package com.luizmatias.findadev.domain.usecases.interest;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import com.luizmatias.findadev.domain.repositories.InterestRepository;

public class GetAllInterestsInteractor {

    private final InterestRepository interestRepository;

    public GetAllInterestsInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public PageResponse<Interest> getInterests(PageRequest pageRequest) {
        return interestRepository.getAllInterests(pageRequest);
    }

}
