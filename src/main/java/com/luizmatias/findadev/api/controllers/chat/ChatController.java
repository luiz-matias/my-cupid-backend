package com.luizmatias.findadev.api.controllers.chat;

import com.luizmatias.findadev.api.dtos.mappers.MessageDTOMapper;
import com.luizmatias.findadev.api.dtos.requests.SendMessageDTO;
import com.luizmatias.findadev.api.dtos.responses.MessageDTO;
import com.luizmatias.findadev.domain.entities.Message;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.exceptions.UserNotInChatException;
import com.luizmatias.findadev.domain.usecases.chat.GetMessagesInteractor;
import com.luizmatias.findadev.domain.usecases.chat.SendMessageInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChatController.CHATS_PATH)
public class ChatController {

    public static final String CHATS_PATH = "/chats";

    final SendMessageInteractor sendMessageInteractor;
    final GetMessagesInteractor getMessagesInteractor;

    public ChatController(SendMessageInteractor sendMessageInteractor, GetMessagesInteractor getMessagesInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
        this.getMessagesInteractor = getMessagesInteractor;
    }

    @Transactional
    @PostMapping(path = "/{chatId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable("chatId") @NotNull @Range(min = 1) Long chatId, @RequestBody @Valid SendMessageDTO sendMessageDTO) throws UserNotInChatException, ResourceNotFoundException {
        Message message = sendMessageInteractor.sendMessage(
                chatId,
                sendMessageDTO.fromUser(),
                sendMessageDTO.message(),
                sendMessageDTO.sentAt()
        );
        return ResponseEntity.ok(MessageDTOMapper.toMessageDTO(message));
    }

    @GetMapping(path = "/{chatId}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable("chatId") @NotNull @Range(min = 1) Long chatId) throws ResourceNotFoundException {
        return ResponseEntity.ok(getMessagesInteractor.getMessages(chatId).stream().map(MessageDTOMapper::toMessageDTO).toList());
    }

}