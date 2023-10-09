package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.ChatRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.List;

public class GetChatsInteractor {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public GetChatsInteractor(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }


    public List<Chat> getChats(Long userId) throws ResourceNotFoundException {
        User user = userRepository.getUser(userId);
        return chatRepository.getUserChats(user);
    }

}
