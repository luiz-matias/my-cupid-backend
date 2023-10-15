package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.MatchEntity;
import com.luizmatias.mycupid.domain.entities.Match;

public class MatchEntityMapper {

    public static Match toMatch(MatchEntity matchEntity) {
        return new Match(
                matchEntity.getId(),
                UserEntityMapper.toUserWithoutLikesAndMatches(matchEntity.getClientUserEntity()),
                UserEntityMapper.toUserWithoutLikesAndMatches(matchEntity.getDeveloperUserEntity()),
                matchEntity.getMatchedAt(),
                matchEntity.getUnmatchedAt()
        );
    }

    public static MatchEntity toMatchEntity(Match match) {
        return new MatchEntity(
                match.getId(),
                UserEntityMapper.toUserEntity(match.getClientUser()),
                UserEntityMapper.toUserEntity(match.getDeveloperUser()),
                match.getMatchedAt(),
                match.getUnmatchedAt()
        );
    }

}
