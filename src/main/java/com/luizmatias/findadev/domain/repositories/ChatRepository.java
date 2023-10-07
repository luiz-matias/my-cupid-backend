package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.entities.User;

import java.util.List;

public interface ChatRepository {

    /**
     * Creates a chat and returns its own reference
     *
     * @param chat the chat to be created
     * @return chat reference that was just created
     */
    Chat createChat(Chat chat);

    /**
     * Send a message inside a chat
     *
     * @param message the message
     */
    Message sendMessage(Message message);

    /**
     * Gets the list of chats for a given user
     *
     * @param user the user to get chats
     * @return list of chats for the given user
     */
    List<Chat> getUserChats(User user);

    /**
     * Gets the list of messages for a given chat
     *
     * @param chat the chat to get messages
     * @return list of messages for the given chat
     */
    List<Message> getChatMessages(Chat chat);

}
