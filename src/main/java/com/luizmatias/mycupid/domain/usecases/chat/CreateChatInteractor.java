package com.luizmatias.mycupid.domain.usecases.chat;

import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;

public class CreateChatInteractor {

    private final ChatRepository chatRepository;

    public CreateChatInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public Chat createChat(Chat chat) {
        return chatRepository.createChat(chat);
    }

}
