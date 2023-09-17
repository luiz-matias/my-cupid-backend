package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;

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
    Match getMatch(Long id) throws ResourceNotFoundException;

    /**
     * updates the match and returns its own reference
     *
     * @param match the data to be updated
     * @return the updated match
     */
    Match updateMatch(Match match) throws ResourceNotFoundException;

    /**
     * deletes a match
     *
     * @param match the match to be deleted
     */
    void deleteMatch(Match match) throws ResourceNotFoundException;

}
