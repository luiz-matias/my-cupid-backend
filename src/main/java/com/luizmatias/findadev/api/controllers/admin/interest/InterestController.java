package com.luizmatias.findadev.api.controllers.admin.interest;

import com.luizmatias.findadev.api.dtos.mappers.InterestDTOMapper;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageRequestDTO;
import com.luizmatias.findadev.api.dtos.mappers.pagination.PageResponseDTO;
import com.luizmatias.findadev.api.dtos.requests.RegisterInterestDTO;
import com.luizmatias.findadev.api.dtos.requests.UpdateInterestDTO;
import com.luizmatias.findadev.api.dtos.responses.InterestDTO;
import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.usecases.interest.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.luizmatias.findadev.api.controllers.admin.AdminController.ADMIN_PATH;

@RestController
@RequestMapping(InterestController.INTEREST_PATH)
public class InterestController {

    public static final String INTEREST_PATH = ADMIN_PATH + "/interest";

    private final GetAllInterestsInteractor getAllInterestsInteractor;
    private final GetInterestInteractor getInterestInteractor;
    private final RegisterInterestInteractor registerInterestInteractor;
    private final UpdateInterestInteractor updateInterestInteractor;
    private final DeleteInterestInteractor deleteInterestInteractor;

    public InterestController(GetAllInterestsInteractor getAllInterestsInteractor, GetInterestInteractor getInterestInteractor, RegisterInterestInteractor registerInterestInteractor, UpdateInterestInteractor updateInterestInteractor, DeleteInterestInteractor deleteInterestInteractor) {
        this.getAllInterestsInteractor = getAllInterestsInteractor;
        this.getInterestInteractor = getInterestInteractor;
        this.registerInterestInteractor = registerInterestInteractor;
        this.updateInterestInteractor = updateInterestInteractor;
        this.deleteInterestInteractor = deleteInterestInteractor;
    }

    @GetMapping(path = {"/", ""})
    public ResponseEntity<PageResponseDTO<InterestDTO>> getAllInterests(@Valid PageRequestDTO pageRequestDTO) {
        PageResponseDTO<InterestDTO> interests = PageMapper.toPageResponseDTO(
                getAllInterestsInteractor.getInterests(PageMapper.toPageRequest(pageRequestDTO)), InterestDTOMapper::toInterestDTO
        );
        return ResponseEntity.ok(interests);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InterestDTO> getInterestById(@PathVariable("id") @NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        Interest interest = getInterestInteractor.getInterest(id);
        return ResponseEntity.ok(InterestDTOMapper.toInterestDTO(interest));
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<InterestDTO> registerInterest(@RequestBody @Valid RegisterInterestDTO interestDTO) {
        Interest interest = interestDTO.toInterest();
        interest = registerInterestInteractor.createInterest(interest);
        return ResponseEntity.ok(InterestDTOMapper.toInterestDTO(interest));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InterestDTO> updateInterest(@PathVariable("id") @NotNull @Range(min = 1) Long id, @RequestBody @Valid UpdateInterestDTO interestDTO) throws ResourceNotFoundException {
        Interest interest = interestDTO.toInterest();
        interest.setId(id);
        interest = updateInterestInteractor.updateInterest(interest);
        return ResponseEntity.ok(InterestDTOMapper.toInterestDTO(interest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<InterestDTO> deleteInterest(@PathVariable("id") @NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        Interest interest = deleteInterestInteractor.deleteInterest(id);
        return ResponseEntity.ok(InterestDTOMapper.toInterestDTO(interest));
    }

}