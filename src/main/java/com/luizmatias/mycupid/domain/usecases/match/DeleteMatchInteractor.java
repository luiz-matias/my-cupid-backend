package com.luizmatias.mycupid.domain.usecases.match;

import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;

public class DeleteMatchInteractor {

    private final MatchRepository matchRepository;

    public DeleteMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void deleteMatch(Match match) throws ResourceNotFoundException {
        matchRepository.deleteMatch(match);
    }

}
