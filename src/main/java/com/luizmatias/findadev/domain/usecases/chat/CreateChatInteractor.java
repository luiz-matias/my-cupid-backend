package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.repositories.ChatRepository;

public class CreateChatInteractor {

    private final ChatRepository chatRepository;

    public CreateChatInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public Chat createChat(Chat chat) {
        return chatRepository.createChat(chat);
    }

}
