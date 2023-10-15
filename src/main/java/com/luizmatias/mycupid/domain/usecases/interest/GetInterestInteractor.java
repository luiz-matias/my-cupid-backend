package com.luizmatias.mycupid.domain.usecases.interest;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;

public class GetInterestInteractor {

    private final InterestRepository interestRepository;

    public GetInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest getInterest(Long id) throws ResourceNotFoundException {
        return interestRepository.getInterestById(id);
    }

}
