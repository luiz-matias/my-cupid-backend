package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

import java.util.Optional;

public class GetMatchInteractor {

    private final MatchRepository matchRepository;

    public GetMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    Optional<Match> getMatch(Long id) {
        return matchRepository.getMatch(id);
    }

}
