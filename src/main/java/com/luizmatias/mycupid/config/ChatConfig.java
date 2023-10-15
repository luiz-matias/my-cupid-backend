package com.luizmatias.mycupid.config;

import com.luizmatias.mycupid.db.repositories.ChatDatabaseRepository;
import com.luizmatias.mycupid.db.repositories.ChatJpaRepository;
import com.luizmatias.mycupid.db.repositories.MessageJpaRepository;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;
import com.luizmatias.mycupid.domain.repositories.UserRepository;
import com.luizmatias.mycupid.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.mycupid.domain.usecases.chat.GetChatsInteractor;
import com.luizmatias.mycupid.domain.usecases.chat.GetMessagesInteractor;
import com.luizmatias.mycupid.domain.usecases.chat.SendMessageInteractor;
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
    GetChatsInteractor getChatsInteractor(ChatRepository chatRepository) {
        return new GetChatsInteractor(chatRepository);
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
