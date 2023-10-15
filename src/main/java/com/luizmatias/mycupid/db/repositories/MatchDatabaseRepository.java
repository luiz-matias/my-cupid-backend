package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.db.models.MatchEntity;
import com.luizmatias.mycupid.db.models.mappers.MatchEntityMapper;
import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;

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
    public Match getMatch(Long id) throws ResourceNotFoundException {
        Optional<Match> match = matchJpaRepository.findById(id).map(MatchEntityMapper::toMatch);

        if (match.isEmpty()) {
            throw new ResourceNotFoundException("Could not find a match with id = " + id);
        }

        return match.get();
    }

    @Override
    public Match updateMatch(Match match) throws ResourceNotFoundException {
        if (!matchJpaRepository.existsById(match.getId())) {
            throw new ResourceNotFoundException("Could not find a match with id = " + match.getId());
        }

        MatchEntity matchEntity = MatchEntityMapper.toMatchEntity(match);
        return MatchEntityMapper.toMatch(matchJpaRepository.save(matchEntity));
    }

    @Override
    public void deleteMatch(Match match) throws ResourceNotFoundException {
        if (!matchJpaRepository.existsById(match.getId())) {
            throw new ResourceNotFoundException("Could not find a match with id = " + match.getId());
        }

        matchJpaRepository.deleteById(match.getId());
    }
}
