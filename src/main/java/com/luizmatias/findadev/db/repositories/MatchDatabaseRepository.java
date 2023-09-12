package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.MatchEntity;
import com.luizmatias.findadev.db.models.mappers.MatchEntityMapper;
import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.repositories.MatchRepository;

import java.util.Optional;

public class MatchDatabaseRepository implements MatchRepository {

    private final MatchJpaRepository matchJpaRepository;

    public MatchDatabaseRepository(MatchJpaRepository matchJpaRepository) {
        this.matchJpaRepository = matchJpaRepository;
    }

    @Override
    public Match createMatch(Match match) {
        MatchEntity matchEntity = MatchEntityMapper.toMatchEntity(match);
        return MatchEntityMapper.toMatch(matchJpaRepository.save(matchEntity));
    }

    @Override
    public Optional<Match> getMatch(Long id) {
        return matchJpaRepository.findById(id).map(MatchEntityMapper::toMatch);
    }

    @Override
    public Match updateMatch(Match match) {
        MatchEntity matchEntity = MatchEntityMapper.toMatchEntity(match);
        return MatchEntityMapper.toMatch(matchJpaRepository.save(matchEntity));
    }

    @Override
    public void deleteMatch(Match match) {
        matchJpaRepository.deleteById(match.getId());
    }
}
