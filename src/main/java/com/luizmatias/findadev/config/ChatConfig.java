package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.ChatDatabaseRepository;
import com.luizmatias.findadev.db.repositories.ChatJpaRepository;
import com.luizmatias.findadev.db.repositories.MessageJpaRepository;
import com.luizmatias.findadev.domain.repositories.ChatRepository;
import com.luizmatias.findadev.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.findadev.domain.usecases.chat.SendMessageInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    ChatRepository chatRepository(ChatJpaRepository chatJpaRepository, MessageJpaRepository messageJpaRepository) {
        return new ChatDatabaseRepository(chatJpaRepository, messageJpaRepository);
    }

    @Bean
    CreateChatInteractor createChatInteractor(ChatRepository chatRepository) {
        return new CreateChatInteractor(chatRepository);
    }

    @Bean
    SendMessageInteractor sendMessageInteractor(ChatRepository chatRepository) {
        return new SendMessageInteractor(chatRepository);
    }

}
