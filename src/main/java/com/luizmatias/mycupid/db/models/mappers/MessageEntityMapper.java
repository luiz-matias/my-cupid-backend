package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.MessageEntity;
import com.luizmatias.mycupid.domain.entities.Message;

public class MessageEntityMapper {

    public static Message toMessage(MessageEntity messageEntity) {
        return new Message(
                messageEntity.getId(),
                ChatEntityMapper.toChat(messageEntity.getChatEntity()),
                UserEntityMapper.toUser(messageEntity.getFromUser()),
                messageEntity.getMessage(),
                messageEntity.getSentAt()
        );
    }

    public static MessageEntity toMessageEntity(Message message) {
        return new MessageEntity(
                message.getId(),
                ChatEntityMapper.toChatEntity(message.getChat()),
                UserEntityMapper.toUserEntity(message.getFrom()),
                message.getMessage(),
                message.getSentAt()
        );
    }

}
