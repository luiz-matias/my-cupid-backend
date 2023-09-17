package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.MatchRepository;
import com.luizmatias.findadev.domain.usecases.like.RemoveLikeInteractor;

public class CreateMatchInteractor {

    private final MatchRepository matchRepository;
    private final RemoveLikeInteractor removeLikeInteractor;

    public CreateMatchInteractor(MatchRepository matchRepository, RemoveLikeInteractor removeLikeInteractor) {
        this.matchRepository = matchRepository;
        this.removeLikeInteractor = removeLikeInteractor;
    }

    public Match createMatch(Match match) throws ResourceNotFoundException {
        removeLikeInteractor.removeLike(match.getClientUser().getId(), match.getDeveloperUser().getId());
        removeLikeInteractor.removeLike(match.getDeveloperUser().getId(), match.getClientUser().getId());
        return matchRepository.createMatch(match);
    }

}
