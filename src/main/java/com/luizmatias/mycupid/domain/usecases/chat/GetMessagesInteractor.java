package com.luizmatias.mycupid.domain.usecases.chat;

import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.entities.Message;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;

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
