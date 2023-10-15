package com.luizmatias.mycupid.domain.repositories;

import com.luizmatias.mycupid.domain.entities.*;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;

public interface ChatRepository {

    /**
     * Creates a chat and returns its own reference
     *
     * @param chat the chat to be created
     * @return chat reference that was just created
     */
    Chat createChat(Chat chat);

    /**
     * Get a chat for a given id
     *
     * @param id the chat id
     * @return chat for the id
     * @throws ResourceNotFoundException in case a chat wasn't found for the given id
     */
    Chat getChatById(Long id) throws ResourceNotFoundException;

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
     * @return paginated response of chats for the given user
     */
    PageResponse<Chat> getUserChats(User user, PageRequest pageRequest);

    /**
     * Gets the list of messages for a given chat
     *
     * @param chat the chat to get messages
     * @return paginated response of messages for the given chat
     */
    PageResponse<Message> getChatMessages(Chat chat, PageRequest pageRequest);

}
