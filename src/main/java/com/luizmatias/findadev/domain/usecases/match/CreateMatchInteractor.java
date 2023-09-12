package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

public class CreateMatchInteractor {

    private final MatchRepository matchRepository;

    public CreateMatchInteractor(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    Match createMatch(Match match) {
        return matchRepository.createMatch(match);
    }

}
