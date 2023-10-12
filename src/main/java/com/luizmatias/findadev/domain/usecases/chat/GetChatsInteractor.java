package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.ChatRepository;

public class GetChatsInteractor {

    private final ChatRepository chatRepository;

    public GetChatsInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public PageResponse<Chat> getChats(User user, PageRequest pageRequest) {
        return chatRepository.getUserChats(user, pageRequest);
    }

}
