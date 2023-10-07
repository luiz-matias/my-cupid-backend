package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.repositories.ChatRepository;

public class SendMessageInteractor {

    private final ChatRepository chatRepository;

    public SendMessageInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Message sendMessage(Message message) {
        return chatRepository.sendMessage(message);
    }

}
