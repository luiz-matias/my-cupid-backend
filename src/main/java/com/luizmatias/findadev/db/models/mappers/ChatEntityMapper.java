package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.ChatEntity;
import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Message;

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
