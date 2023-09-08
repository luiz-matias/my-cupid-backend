package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.MatchEntity;
import com.luizmatias.findadev.domain.entities.Match;

public class MatchEntityMapper {

    static Match toMatch(MatchEntity matchEntity) {
        return new Match(
                matchEntity.getId(),
                UserEntityMapper.toUserWithoutLikesAndMatches(matchEntity.getClientUserEntity()),
                UserEntityMapper.toUserWithoutLikesAndMatches(matchEntity.getDeveloperUserEntity()),
                matchEntity.getMatchedAt(),
                matchEntity.getUnmatchedAt()
        );
    }

    static MatchEntity toMatchEntity(Match match) {
        return new MatchEntity(
                match.getId(),
                UserEntityMapper.toUserEntity(match.getClientUser()),
                UserEntityMapper.toUserEntity(match.getDeveloperUser()),
                match.getMatchedAt(),
                match.getUnmatchedAt()
        );
    }

}
