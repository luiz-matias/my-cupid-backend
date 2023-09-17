package com.luizmatias.findadev.api.controllers.like;

import com.luizmatias.findadev.api.dtos.mappers.MatchDTOMapper;
import com.luizmatias.findadev.api.dtos.requests.RegisterLikeDTO;
import com.luizmatias.findadev.api.dtos.requests.RemoveLikeDTO;
import com.luizmatias.findadev.api.dtos.responses.RegisterLikeResponseDTO;
import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserException;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserTypeException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.usecases.like.RegisterLikeInteractor;
import com.luizmatias.findadev.domain.usecases.like.RemoveLikeInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(LikeController.LIKES_PATH)
public class LikeController {

    public static final String LIKES_PATH = "/likes";

    final RegisterLikeInteractor registerLikeInteractor;
    final RemoveLikeInteractor removeLikeInteractor;

    public LikeController(RegisterLikeInteractor registerLikeInteractor, RemoveLikeInteractor removeLikeInteractor) {
        this.registerLikeInteractor = registerLikeInteractor;
        this.removeLikeInteractor = removeLikeInteractor;
    }

    @Transactional
    @PostMapping(path = {"", "/"})
    public ResponseEntity<RegisterLikeResponseDTO> registerLike(@RequestBody @Valid RegisterLikeDTO registerLikeDTO) throws ResourceNotFoundException, LikeOnSameUserException, LikeOnSameUserTypeException {
        Optional<Match> match = registerLikeInteractor.registerLike(registerLikeDTO.fromId(), registerLikeDTO.toId());
        return ResponseEntity.ok(new RegisterLikeResponseDTO(
                match.isPresent(),
                match.map(MatchDTOMapper::toMatchDTO).orElse(null)
        ));
    }

    @Transactional
    @DeleteMapping(path = {"", "/"})
    public ResponseEntity<RegisterLikeResponseDTO> removeLike(@RequestBody @Valid RemoveLikeDTO removeLikeDTO) throws ResourceNotFoundException {
        removeLikeInteractor.removeLike(removeLikeDTO.fromId(), removeLikeDTO.toId());
        return ResponseEntity.ok().build();
    }

}