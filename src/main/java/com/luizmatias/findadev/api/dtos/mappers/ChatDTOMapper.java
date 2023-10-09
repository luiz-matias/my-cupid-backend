package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.responses.ChatDTO;
import com.luizmatias.findadev.domain.entities.Chat;

public class ChatDTOMapper {

    public static ChatDTO toChatDTO(Chat chat) {
        return new ChatDTO(
                chat.getId(),
                UserDTOMapper.toUserDTOWithoutLikesAndMatches(chat.getFirstUser()),
                UserDTOMapper.toUserDTOWithoutLikesAndMatches(chat.getSecondUser()),
                chat.getCreatedAt()
        );
    }

}
