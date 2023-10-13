package com.luizmatias.findadev.api.controllers.user;

import com.luizmatias.findadev.api.dtos.mappers.ChatDTOMapper;
import com.luizmatias.findadev.api.dtos.mappers.UserDTOMapper;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageRequestDTO;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageResponseDTO;
import com.luizmatias.findadev.api.dtos.requests.ChangePasswordDTO;
import com.luizmatias.findadev.api.dtos.requests.ConfirmChangePasswordDTO;
import com.luizmatias.findadev.api.dtos.requests.UpdateUserDTO;
import com.luizmatias.findadev.api.dtos.requests.UserInterestDTO;
import com.luizmatias.findadev.api.dtos.responses.ChatDTO;
import com.luizmatias.findadev.api.dtos.responses.UserDTO;
import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.*;
import com.luizmatias.findadev.domain.usecases.chat.GetChatsInteractor;
import com.luizmatias.findadev.domain.usecases.user.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.USERS_PATH)
public class UserController {

    public static final String USERS_PATH = "/user";

    private final UpdateUserInteractor updateUserInteractor;
    private final ChangePasswordInteractor changePasswordInteractor;
    private final ConfirmChangePasswordInteractor confirmChangePasswordInteractor;
    private final DeleteUserInteractor deleteUserInteractor;
    private final GetChatsInteractor getChatsInteractor;
    private final AddUserInterestInteractor addUserInterestInteractor;
    private final RemoveUserInterestInteractor removeUserInterestInteractor;

    public UserController(UpdateUserInteractor updateUserInteractor,
                          ChangePasswordInteractor changePasswordInteractor, ConfirmChangePasswordInteractor confirmChangePasswordInteractor, DeleteUserInteractor deleteUserInteractor,
                          GetChatsInteractor getChatsInteractor,
                          AddUserInterestInteractor addUserInterestInteractor, RemoveUserInterestInteractor removeUserInterestInteractor) {
        this.updateUserInteractor = updateUserInteractor;
        this.changePasswordInteractor = changePasswordInteractor;
        this.confirmChangePasswordInteractor = confirmChangePasswordInteractor;
        this.deleteUserInteractor = deleteUserInteractor;
        this.getChatsInteractor = getChatsInteractor;
        this.addUserInterestInteractor = addUserInterestInteractor;
        this.removeUserInterestInteractor = removeUserInterestInteractor;
    }

    @Transactional
    @PutMapping(path = {"/", ""})
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UpdateUserDTO updateUserDTO, @AuthenticationPrincipal UserEntity userEntity) throws ResourceNotFoundException {
        User user = updateUserDTO.toUser();
        user = updateUserInteractor.updateUser(userEntity.toUser(), user);
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

    @Transactional
    @PutMapping(path = "/password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO, @AuthenticationPrincipal UserEntity userEntity) throws PasswordMismatchException, FailedToSendNotificationException {
        changePasswordInteractor.changePassword(userEntity.toUser(), changePasswordDTO.oldPassword());
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping(path = "/password-confirmation")
    public ResponseEntity<UserDTO> confirmChangePassword(@RequestBody @Valid ConfirmChangePasswordDTO confirmChangePasswordDTO, @AuthenticationPrincipal UserEntity userEntity) throws FailedToSendNotificationException, InvalidTokenException, ResourceNotFoundException {
        confirmChangePasswordInteractor.confirmChangePassword(confirmChangePasswordDTO.token(), confirmChangePasswordDTO.newPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/", ""})
    public ResponseEntity<UserDTO> deleteUser(@AuthenticationPrincipal UserEntity userEntity) throws ResourceNotFoundException {
        deleteUserInteractor.deleteUser(userEntity.toUser());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(userEntity.toUser()));
    }

    @GetMapping(path = "/chat")
    public ResponseEntity<PageResponseDTO<ChatDTO>> getUserChats(@AuthenticationPrincipal UserEntity userEntity, @Valid PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ChatDTO> chats = PageMapper.toPageResponseDTO(
                getChatsInteractor.getChats(userEntity.toUser(), PageMapper.toPageRequest(pageRequestDTO)),
                ChatDTOMapper::toChatDTO
        );
        return ResponseEntity.ok(chats);
    }

    @Transactional
    @PostMapping(path = "/interest")
    public ResponseEntity<UserDTO> addUserInterest(@AuthenticationPrincipal UserEntity userEntity, @RequestBody @Valid UserInterestDTO userInterestDTO) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        User user = addUserInterestInteractor.addUserInterest(userEntity.toUser(), userInterestDTO.interestId());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

    @Transactional
    @DeleteMapping(path = "/interest")
    public ResponseEntity<UserDTO> deleteUserInterest(@AuthenticationPrincipal UserEntity userEntity, @RequestBody @Valid UserInterestDTO userInterestDTO) throws ResourceNotFoundException {
        User user = removeUserInterestInteractor.removeUserInterest(userEntity.toUser(), userInterestDTO.interestId());
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

}
