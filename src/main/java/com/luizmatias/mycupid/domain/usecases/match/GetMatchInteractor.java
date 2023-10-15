package com.luizmatias.mycupid.domain.usecases.match;

import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;

public class GetMatchInteractor {

    private final MatchRepository matchRepository;

    public GetMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatch(Long id) throws ResourceNotFoundException {
        return matchRepository.getMatch(id);
    }

}
