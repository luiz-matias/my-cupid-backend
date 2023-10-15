package com.luizmatias.mycupid.api.controllers.admin;

import com.luizmatias.mycupid.api.dtos.mappers.UserDTOMapper;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageRequestDTO;
import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageResponseDTO;
import com.luizmatias.mycupid.api.dtos.responses.UserDTO;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.usecases.user.GetAllUsersInteractor;
import com.luizmatias.mycupid.domain.usecases.user.GetUserInteractor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdminController.ADMIN_PATH)
public class AdminController {

    public static final String ADMIN_PATH = "/admin";

    private final GetAllUsersInteractor getAllUsersInteractor;
    private final GetUserInteractor getUserInteractor;

    public AdminController(
            GetAllUsersInteractor getAllUsersInteractor,
            GetUserInteractor getUserInteractor
    ) {
        this.getAllUsersInteractor = getAllUsersInteractor;
        this.getUserInteractor = getUserInteractor;
    }

    @GetMapping(path = {"/users", "/users/"})
    public ResponseEntity<PageResponseDTO<UserDTO>> getAllUsers(@Valid PageRequestDTO pageRequestDTO) {
        PageResponseDTO<UserDTO> users = PageMapper.toPageResponseDTO(
                getAllUsersInteractor.getAllUsers(PageMapper.toPageRequest(pageRequestDTO)), UserDTOMapper::toUserDTO
        );
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") @NotNull @Min(1) @Max(Long.MAX_VALUE) Long id) throws ResourceNotFoundException {
        User user = getUserInteractor.getUser(id);
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

}