package com.luizmatias.findadev.domain.usecases.interest;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.InterestRepository;

public class DeleteInterestInteractor {

    private final InterestRepository interestRepository;

    public DeleteInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest deleteInterest(Long id) throws ResourceNotFoundException {
        Interest interest = interestRepository.getInterestById(id);
        interestRepository.deleteInterest(interest);
        return interest;
    }

}
