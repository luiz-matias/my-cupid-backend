package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.ChatDatabaseRepository;
import com.luizmatias.findadev.db.repositories.ChatJpaRepository;
import com.luizmatias.findadev.db.repositories.MessageJpaRepository;
import com.luizmatias.findadev.domain.repositories.ChatRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.findadev.domain.usecases.chat.GetChatsInteractor;
import com.luizmatias.findadev.domain.usecases.chat.GetMessagesInteractor;
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
    GetChatsInteractor getChatsInteractor(ChatRepository chatRepository, UserRepository userRepository) {
        return new GetChatsInteractor(chatRepository, userRepository);
    }

    @Bean
    GetMessagesInteractor getMessagesInteractor(ChatRepository chatRepository) {
        return new GetMessagesInteractor(chatRepository);
    }

    @Bean
    SendMessageInteractor sendMessageInteractor(ChatRepository chatRepository, UserRepository userRepository) {
        return new SendMessageInteractor(chatRepository, userRepository);
    }

}
