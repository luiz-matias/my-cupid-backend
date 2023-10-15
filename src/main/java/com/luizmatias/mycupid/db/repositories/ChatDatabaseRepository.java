package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.mycupid.db.models.ChatEntity;
import com.luizmatias.mycupid.db.models.MessageEntity;
import com.luizmatias.mycupid.db.models.mappers.ChatEntityMapper;
import com.luizmatias.mycupid.db.models.mappers.MessageEntityMapper;
import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.entities.Message;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.ChatRepository;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class ChatDatabaseRepository implements ChatRepository {

    private final ChatJpaRepository chatJpaRepository;
    private final MessageJpaRepository messageJpaRepository;

    public ChatDatabaseRepository(ChatJpaRepository chatJpaRepository, MessageJpaRepository messageJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
        this.messageJpaRepository = messageJpaRepository;
    }

    @Override
    public Chat createChat(Chat chat) {
        ChatEntity chatEntity = ChatEntityMapper.toChatEntity(chat);
        return ChatEntityMapper.toChat(chatJpaRepository.save(chatEntity));
    }

    @Override
    public Chat getChatById(Long id) throws ResourceNotFoundException {
        Optional<ChatEntity> chat = chatJpaRepository.findById(id);

        if (chat.isEmpty()) {
            throw new ResourceNotFoundException("cannot find a chat with id = " + id);
        }

        return ChatEntityMapper.toChat(chat.get());
    }

    @Override
    public Message sendMessage(Message message) {
        MessageEntity messageEntity = MessageEntityMapper.toMessageEntity(message);
        return MessageEntityMapper.toMessage(messageJpaRepository.save(messageEntity));
    }

    @Override
    public PageResponse<Chat> getUserChats(User user, PageRequest pageRequest) {
        return PageMapper.toPageResponse(
                chatJpaRepository
                        .findByUserId(
                                user.getId(),
                                PageMapper.toJpaPageRequest(pageRequest).withSort(Sort.by(Sort.Direction.DESC, "id"))
                        )
                        .map(ChatEntityMapper::toChat)
        );
    }

    @Override
    public PageResponse<Message> getChatMessages(Chat chat, PageRequest pageRequest) {
        return PageMapper.toPageResponse(
                messageJpaRepository
                        .findByChatId(chat.getId(), PageMapper.toJpaPageRequest(pageRequest).withSort(Sort.by(Sort.Direction.DESC, "sentAt")))
                        .map(MessageEntityMapper::toMessage)
        );
    }

}
