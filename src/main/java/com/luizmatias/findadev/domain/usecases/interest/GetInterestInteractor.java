package com.luizmatias.findadev.domain.usecases.interest;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.InterestRepository;

public class GetInterestInteractor {

    private final InterestRepository interestRepository;

    public GetInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest getInterest(Long id) throws ResourceNotFoundException {
        return interestRepository.getInterestById(id);
    }

}
