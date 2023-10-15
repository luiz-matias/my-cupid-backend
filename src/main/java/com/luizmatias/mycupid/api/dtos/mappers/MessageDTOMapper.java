package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.api.dtos.responses.MessageDTO;
import com.luizmatias.mycupid.domain.entities.Message;

public class MessageDTOMapper {

    public static MessageDTO toMessageDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getChat().getId(),
                message.getFrom().getId(),
                message.getMessage(),
                message.getSentAt()
        );
    }

}
