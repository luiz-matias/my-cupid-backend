package com.luizmatias.mycupid.domain.usecases.interest;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;

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
