package com.luizmatias.mycupid.domain.usecases.match;

import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;

public class UpdateMatchInteractor {

    private final MatchRepository matchRepository;

    public UpdateMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match updateMatch(Match match) throws ResourceNotFoundException {
        return matchRepository.updateMatch(match);
    }

}
