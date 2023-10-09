package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.ChatRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.List;

public class GetChatsInteractor {

    private final ChatRepository chatRepository;

    public GetChatsInteractor(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public List<Chat> getChats(User user) {
        return chatRepository.getUserChats(user);
    }

}
