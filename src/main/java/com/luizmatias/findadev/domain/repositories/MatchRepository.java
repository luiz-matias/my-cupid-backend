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
     * @return a reference for the match
     * @throws ResourceNotFoundException in case the match doesn't exist
     */
    Match getMatch(Long id) throws ResourceNotFoundException;

    /**
     * updates the match and returns its own reference
     *
     * @param match the data to be updated
     * @return the updated match
     * @throws ResourceNotFoundException in case a user involved in the match doesn't exist
     */
    Match updateMatch(Match match) throws ResourceNotFoundException;

    /**
     * deletes a match
     *
     * @param match the match to be deleted
     * @throws ResourceNotFoundException in case a user involved in the match doesn't exist
     */
    void deleteMatch(Match match) throws ResourceNotFoundException;

}
