package com.luizmatias.findadev.domain.usecases.interest;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.InterestRepository;

public class UpdateInterestInteractor {

    private final InterestRepository interestRepository;

    public UpdateInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest updateInterest(Interest interest) throws ResourceNotFoundException {
        return interestRepository.updateInterest(interest);
    }

}
