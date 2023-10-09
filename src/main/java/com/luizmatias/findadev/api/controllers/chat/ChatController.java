package com.luizmatias.findadev.api.controllers.chat;

import com.luizmatias.findadev.api.dtos.mappers.MessageDTOMapper;
import com.luizmatias.findadev.api.dtos.requests.SendMessageDTO;
import com.luizmatias.findadev.api.dtos.responses.MessageDTO;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.exceptions.UserNotInChatException;
import com.luizmatias.findadev.domain.usecases.chat.SendMessageInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ChatController.CHATS_PATH)
public class ChatController {

    public static final String CHATS_PATH = "/chats";

    final SendMessageInteractor sendMessageInteractor;

    public ChatController(SendMessageInteractor sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    @Transactional
    @PostMapping(path = "/{chatId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable("chatId") @Range(min = 1) Long chatId, @RequestBody @Valid SendMessageDTO sendMessageDTO) throws UserNotInChatException, ResourceNotFoundException {
        Message message = sendMessageInteractor.sendMessage(
                chatId,
                sendMessageDTO.fromUser(),
                sendMessageDTO.message(),
                sendMessageDTO.sentAt()
        );
        return ResponseEntity.ok(MessageDTOMapper.toMessageDTO(message));
    }

}