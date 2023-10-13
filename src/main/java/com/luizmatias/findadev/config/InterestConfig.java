package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.InterestDatabaseRepository;
import com.luizmatias.findadev.db.repositories.InterestJpaRepository;
import com.luizmatias.findadev.domain.repositories.InterestRepository;
import com.luizmatias.findadev.domain.usecases.interest.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterestConfig {

    @Bean
    InterestRepository interestRepository(InterestJpaRepository interestJpaRepository) {
        return new InterestDatabaseRepository(interestJpaRepository);
    }

    @Bean
    GetAllInterestsInteractor getAllInterestsInteractor(InterestRepository interestRepository) {
        return new GetAllInterestsInteractor(interestRepository);
    }

    @Bean
    GetInterestInteractor getInterestInteractor(InterestRepository interestRepository) {
        return new GetInterestInteractor(interestRepository);
    }

    @Bean
    RegisterInterestInteractor registerInterestInteractor(InterestRepository interestRepository) {
        return new RegisterInterestInteractor(interestRepository);
    }

    @Bean
    UpdateInterestInteractor updateInterestInteractor(InterestRepository interestRepository) {
        return new UpdateInterestInteractor(interestRepository);
    }

    @Bean
    DeleteInterestInteractor deleteInterestInteractor(InterestRepository interestRepository) {
        return new DeleteInterestInteractor(interestRepository);
    }

}
