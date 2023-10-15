package com.luizmatias.mycupid.api.controllers.chat;

import com.luizmatias.mycupid.api.controllers.user.UserController;
import com.luizmatias.mycupid.api.dtos.mappers.MessageDTOMapper;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageRequestDTO;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageResponseDTO;
import com.luizmatias.mycupid.api.dtos.requests.SendMessageDTO;
import com.luizmatias.mycupid.api.dtos.responses.MessageDTO;
import com.luizmatias.mycupid.db.models.UserEntity;
import com.luizmatias.mycupid.domain.entities.Message;
import com.luizmatias.mycupid.domain.exceptions.NotAuthorizedException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.usecases.chat.GetMessagesInteractor;
import com.luizmatias.mycupid.domain.usecases.chat.SendMessageInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ChatController.CHATS_PATH)
public class ChatController {

    public static final String CHATS_PATH = UserController.USERS_PATH + "/chat";

    final SendMessageInteractor sendMessageInteractor;
    final GetMessagesInteractor getMessagesInteractor;

    public ChatController(SendMessageInteractor sendMessageInteractor, GetMessagesInteractor getMessagesInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
        this.getMessagesInteractor = getMessagesInteractor;
    }

    @Transactional
    @PostMapping(path = "/{chatId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable("chatId") @NotNull @Range(min = 1) Long chatId, @RequestBody @Valid SendMessageDTO sendMessageDTO, @AuthenticationPrincipal UserEntity userEntity) throws NotAuthorizedException, ResourceNotFoundException {
        Message message = sendMessageInteractor.sendMessage(
                chatId,
                userEntity.toUser(),
                sendMessageDTO.message(),
                sendMessageDTO.sentAt()
        );
        return ResponseEntity.ok(MessageDTOMapper.toMessageDTO(message));
    }

    @GetMapping(path = "/{chatId}")
    public ResponseEntity<PageResponseDTO<MessageDTO>> getMessages(@PathVariable("chatId") @NotNull @Range(min = 1) Long chatId, @AuthenticationPrincipal UserEntity userEntity, @Valid PageRequestDTO pageRequestDTO) throws ResourceNotFoundException {
        PageResponseDTO<MessageDTO> messages = PageMapper.toPageResponseDTO(
                getMessagesInteractor.getMessages(chatId, PageMapper.toPageRequest(pageRequestDTO)), MessageDTOMapper::toMessageDTO
        );
        return ResponseEntity.ok(messages);
    }

}
