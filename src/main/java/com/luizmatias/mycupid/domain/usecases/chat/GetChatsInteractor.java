package com.luizmatias.mycupid.domain.usecases.chat;

import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;

public class GetChatsInteractor {

    private final ChatRepository chatRepository;

    public GetChatsInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public PageResponse<Chat> getChats(User user, PageRequest pageRequest) {
        return chatRepository.getUserChats(user, pageRequest);
    }

}
