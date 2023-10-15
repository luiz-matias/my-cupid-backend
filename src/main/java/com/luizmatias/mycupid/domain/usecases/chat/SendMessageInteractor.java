package com.luizmatias.mycupid.domain.usecases.chat;

import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.entities.Message;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.NotAuthorizedException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;
import com.luizmatias.mycupid.domain.repositories.UserRepository;

import java.util.Date;
import java.util.Objects;

public class SendMessageInteractor {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public SendMessageInteractor(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(Long chatId, User fromUser, String messageContent, Date sentAt) throws ResourceNotFoundException, NotAuthorizedException {
        Chat chat = chatRepository.getChatById(chatId);

        if (!Objects.equals(chat.getFirstUser().getId(), fromUser.getId()) && !Objects.equals(chat.getSecondUser().getId(), fromUser.getId())) {
            throw new NotAuthorizedException("user isn't part of that chat room");
        }

        if (sentAt == null) {
            sentAt = new Date();
        }

        Message message = new Message(
                null,
                chat,
                fromUser,
                messageContent,
                sentAt
        );

        return chatRepository.sendMessage(message);
    }

}
