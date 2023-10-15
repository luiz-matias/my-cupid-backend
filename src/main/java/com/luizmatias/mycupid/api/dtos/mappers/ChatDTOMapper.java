package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.api.dtos.responses.ChatDTO;
import com.luizmatias.mycupid.domain.entities.Chat;

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
