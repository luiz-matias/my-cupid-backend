package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.MatchDTO;
import com.luizmatias.findadev.domain.entities.Match;

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
                UserDTOMapper.toUserDTO(match.getClientUser()),
                UserDTOMapper.toUserDTO(match.getDeveloperUser()),
                match.getMatchedAt(),
                match.getUnmatchedAt()
        );
    }

}
