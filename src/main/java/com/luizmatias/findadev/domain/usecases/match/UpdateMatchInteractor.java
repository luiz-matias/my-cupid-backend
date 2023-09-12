package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

public class UpdateMatchInteractor {

    private final MatchRepository matchRepository;

    public UpdateMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    Match updateMatch(Match match) {
        return matchRepository.updateMatch(match);
    }

}
