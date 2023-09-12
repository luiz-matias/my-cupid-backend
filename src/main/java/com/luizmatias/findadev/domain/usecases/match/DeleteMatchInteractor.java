package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

public class DeleteMatchInteractor {

    private final MatchRepository matchRepository;

    public DeleteMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    void deleteMatch(Match match) {
        matchRepository.deleteMatch(match);
    }

}
