package com.luizmatias.mycupid.domain.usecases.interest;

import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;

public class RegisterInterestInteractor {

    private final InterestRepository interestRepository;

    public RegisterInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest createInterest(Interest interest) {
        return interestRepository.createInterest(interest);
    }

}
