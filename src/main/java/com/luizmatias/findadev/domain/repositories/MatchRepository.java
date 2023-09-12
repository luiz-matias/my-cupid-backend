package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.Match;

import java.util.Optional;

public interface MatchRepository {

    /**
     * Creates a user and returns its own reference
     *
     * @param match the match to be created
     * @return Match reference that was just created
     */
    Match createMatch(Match match);

    /**
     * Gets a specific match filtering by id
     *
     * @param id the id of the match
     * @return an optional reference for the match in case a match with its id doesn't exist
     */
    Optional<Match> getMatch(Long id);

    /**
     * updates the match and returns its own reference
     *
     * @param match the data to be updated
     * @return the updated match
     */
    Match updateMatch(Match match);

    /**
     * deletes a match
     *
     * @param match the match to be deleted
     */
    void deleteMatch(Match match);

}
