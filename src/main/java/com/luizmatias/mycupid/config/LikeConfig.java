package com.luizmatias.mycupid.config;

import com.luizmatias.mycupid.domain.repositories.UserRepository;
import com.luizmatias.mycupid.domain.usecases.like.RegisterLikeInteractor;
import com.luizmatias.mycupid.domain.usecases.like.RemoveLikeInteractor;
import com.luizmatias.mycupid.domain.usecases.match.CreateMatchInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LikeConfig {

    @Bean
    RegisterLikeInteractor registerLikeInteractor(UserRepository userRepository, CreateMatchInteractor createMatchInteractor) {
        return new RegisterLikeInteractor(userRepository, createMatchInteractor);
    }

    @Bean
    RemoveLikeInteractor removeLikeInteractor(UserRepository userRepository) {
        return new RemoveLikeInteractor(userRepository);
    }
}
