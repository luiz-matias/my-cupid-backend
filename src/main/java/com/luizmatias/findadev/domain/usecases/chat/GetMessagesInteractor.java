package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.ChatRepository;

import java.util.List;

public class GetMessagesInteractor {

    private final ChatRepository chatRepository;

    public GetMessagesInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public List<Message> getMessages(Long chatId) throws ResourceNotFoundException {
        Chat chat = chatRepository.getChatById(chatId);
        return chatRepository.getChatMessages(chat);
    }

}
