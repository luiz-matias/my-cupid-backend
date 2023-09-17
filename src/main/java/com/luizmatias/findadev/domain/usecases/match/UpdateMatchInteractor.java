package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

public class UpdateMatchInteractor {

    private final MatchRepository matchRepository;

    public UpdateMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match updateMatch(Match match) throws ResourceNotFoundException {
        return matchRepository.updateMatch(match);
    }

}
