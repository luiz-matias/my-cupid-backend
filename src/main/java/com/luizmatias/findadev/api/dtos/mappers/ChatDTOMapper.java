package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.responses.ChatDTO;
import com.luizmatias.findadev.domain.entities.Chat;

public class ChatDTOMapper {

    public static ChatDTO toChatDTO(Chat chat) {
        return new ChatDTO(
                chat.getId(),
                UserDTOMapper.toUserDTO(chat.getFirstUser()),
                UserDTOMapper.toUserDTO(chat.getSecondUser()),
                chat.getCreatedAt()
        );
    }

}
