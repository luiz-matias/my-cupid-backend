package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.MessageEntity;
import com.luizmatias.findadev.domain.entities.Message;

public class MessageEntityMapper {

    public static Message toMessage(MessageEntity messageEntity) {
        return new Message(
                messageEntity.getId(),
                ChatEntityMapper.toChat(messageEntity.getChatEntity()),
                UserEntityMapper.toUser(messageEntity.getFromUser()),
                UserEntityMapper.toUser(messageEntity.getToUser()),
                messageEntity.getMessage(),
                messageEntity.getSentAt()
        );
    }

    public static MessageEntity toMessageEntity(Message message) {
        return new MessageEntity(
                message.getId(),
                ChatEntityMapper.toChatEntity(message.getChat()),
                UserEntityMapper.toUserEntity(message.getFrom()),
                UserEntityMapper.toUserEntity(message.getTo()),
                message.getMessage(),
                message.getSentAt()
        );
    }

}
