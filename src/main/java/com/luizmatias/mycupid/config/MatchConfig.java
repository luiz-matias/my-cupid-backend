package com.luizmatias.mycupid.config;

import com.luizmatias.mycupid.db.repositories.MatchDatabaseRepository;
import com.luizmatias.mycupid.db.repositories.MatchJpaRepository;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.mycupid.domain.usecases.like.RemoveLikeInteractor;
import com.luizmatias.mycupid.domain.usecases.match.CreateMatchInteractor;
import com.luizmatias.mycupid.domain.usecases.match.DeleteMatchInteractor;
import com.luizmatias.mycupid.domain.usecases.match.GetMatchInteractor;
import com.luizmatias.mycupid.domain.usecases.match.UpdateMatchInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchConfig {

    @Bean
    CreateMatchInteractor createMatchInteractor(MatchRepository matchRepository, RemoveLikeInteractor removeLikeInteractor, CreateChatInteractor createChatInteractor, NotificationSenderRepository notificationSenderRepository) {
        return new CreateMatchInteractor(matchRepository, removeLikeInteractor, createChatInteractor, notificationSenderRepository);
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
