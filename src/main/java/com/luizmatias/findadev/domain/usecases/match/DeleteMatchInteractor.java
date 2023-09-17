package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

public class DeleteMatchInteractor {

    private final MatchRepository matchRepository;

    public DeleteMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void deleteMatch(Match match) throws ResourceNotFoundException {
        matchRepository.deleteMatch(match);
    }

}
