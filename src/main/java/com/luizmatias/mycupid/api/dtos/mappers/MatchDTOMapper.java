package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.api.dtos.responses.MatchDTO;
import com.luizmatias.mycupid.domain.entities.Match;

public class MatchDTOMapper {

    public static Match toMatch(MatchDTO matchDTO) {
        return new Match(
                matchDTO.id(),
                UserDTOMapper.toUserWithoutLikesAndMatches(matchDTO.clientUser()),
                UserDTOMapper.toUserWithoutLikesAndMatches(matchDTO.developerUser()),
                matchDTO.matchedAt(),
                matchDTO.unmatchedAt()
        );
    }

    public static MatchDTO toMatchDTO(Match match) {
        return new MatchDTO(
                match.getId(),
                UserDTOMapper.toUserDTOWithoutLikesAndMatches(match.getClientUser()),
                UserDTOMapper.toUserDTOWithoutLikesAndMatches(match.getDeveloperUser()),
                match.getMatchedAt(),
                match.getUnmatchedAt()
        );
    }

}
