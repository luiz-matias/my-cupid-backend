package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.ChatEntity;
import com.luizmatias.mycupid.domain.entities.Chat;

public class ChatEntityMapper {

    public static Chat toChat(ChatEntity chatEntity) {
        return new Chat(
                chatEntity.getId(),
                UserEntityMapper.toUserWithoutLikesAndMatches(chatEntity.getFirstUserEntity()),
                UserEntityMapper.toUserWithoutLikesAndMatches(chatEntity.getSecondUserEntity()),
                chatEntity.getCreatedAt()
        );
    }

    public static ChatEntity toChatEntity(Chat chat) {
        return new ChatEntity(
                chat.getId(),
                UserEntityMapper.toUserEntity(chat.getFirstUser()),
                UserEntityMapper.toUserEntity(chat.getSecondUser()),
                chat.getCreatedAt()
        );
    }

}
