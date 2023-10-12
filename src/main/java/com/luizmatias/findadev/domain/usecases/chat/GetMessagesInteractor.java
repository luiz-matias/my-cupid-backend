package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.ChatRepository;

public class GetMessagesInteractor {

    private final ChatRepository chatRepository;

    public GetMessagesInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public PageResponse<Message> getMessages(Long chatId, PageRequest pageRequest) throws ResourceNotFoundException {
        Chat chat = chatRepository.getChatById(chatId);
        return chatRepository.getChatMessages(chat, pageRequest);
    }

}
