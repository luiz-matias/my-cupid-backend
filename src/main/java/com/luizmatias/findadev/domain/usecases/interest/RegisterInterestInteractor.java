package com.luizmatias.findadev.domain.usecases.interest;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.repositories.InterestRepository;

public class RegisterInterestInteractor {

    private final InterestRepository interestRepository;

    public RegisterInterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest createInterest(Interest interest) {
        return interestRepository.createInterest(interest);
    }

}
