package com.luizmatias.mycupid.domain.usecases.interest;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;

public class UpdateInterestInteractor {

    private final InterestRepository interestRepository;

    public UpdateInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest updateInterest(Interest interest) throws ResourceNotFoundException {
        return interestRepository.updateInterest(interest);
    }

}
