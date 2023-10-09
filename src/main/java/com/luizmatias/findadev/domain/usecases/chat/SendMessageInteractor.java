package com.luizmatias.findadev.domain.usecases.chat;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.exceptions.UserNotInChatException;
import com.luizmatias.findadev.domain.repositories.ChatRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.Date;
import java.util.Objects;

public class SendMessageInteractor {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public SendMessageInteractor(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(Long chatId, Long fromUserId, String messageContent, Date sentAt) throws ResourceNotFoundException, UserNotInChatException {
        Chat chat = chatRepository.getChatById(chatId);
        User user = userRepository.getUser(fromUserId);

        if (!Objects.equals(chat.getFirstUser().getId(), user.getId()) && !Objects.equals(chat.getSecondUser().getId(), user.getId())) {
            throw new UserNotInChatException("user isn't part of that chat room");
        }

        if (sentAt == null) {
            sentAt = new Date();
        }

        Message message = new Message(
                null,
                chat,
                user,
                messageContent,
                sentAt
        );

        return chatRepository.sendMessage(message);
    }

}
