package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.responses.MessageDTO;
import com.luizmatias.findadev.domain.entities.Message;

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
