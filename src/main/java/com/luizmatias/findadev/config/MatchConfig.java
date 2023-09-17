package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.MatchDatabaseRepository;
import com.luizmatias.findadev.db.repositories.MatchJpaRepository;
import com.luizmatias.findadev.domain.repositories.MatchRepository;
import com.luizmatias.findadev.domain.usecases.like.RemoveLikeInteractor;
import com.luizmatias.findadev.domain.usecases.match.CreateMatchInteractor;
import com.luizmatias.findadev.domain.usecases.match.DeleteMatchInteractor;
import com.luizmatias.findadev.domain.usecases.match.GetMatchInteractor;
import com.luizmatias.findadev.domain.usecases.match.UpdateMatchInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchConfig {

    @Bean
    CreateMatchInteractor createMatchInteractor(MatchRepository matchRepository, RemoveLikeInteractor removeLikeInteractor) {
        return new CreateMatchInteractor(matchRepository, removeLikeInteractor);
    }

    @Bean
    GetMatchInteractor getMatchInteractor(MatchRepository matchRepository) {
        return new GetMatchInteractor(matchRepository);
    }

    @Bean
    UpdateMatchInteractor updateMatchInteractor(MatchRepository matchRepository) {
        return new UpdateMatchInteractor(matchRepository);
    }

    @Bean
    DeleteMatchInteractor deleteMatchInteractor(MatchRepository matchRepository) {
        return new DeleteMatchInteractor(matchRepository);
    }

    @Bean
    MatchRepository matchRepository(MatchJpaRepository matchJpaRepository) {
        return new MatchDatabaseRepository(matchJpaRepository);
    }
}
