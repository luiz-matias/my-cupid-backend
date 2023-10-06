package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;

import java.util.Optional;

public interface UserTemporaryTokenRepository {

    /**
     * Creates a userTemporaryToken and returns its own reference
     *
     * @param userTemporaryToken the userTemporaryToken to be created
     * @return userTemporaryToken reference that was just created
     */
    UserTemporaryToken createTemporaryToken(UserTemporaryToken userTemporaryToken);

    /**
     * Gets a specific userTemporaryToken filtering by id
     *
     * @param id the id of the userTemporaryToken
     * @return a reference for the userTemporaryToken
     * @throws ResourceNotFoundException in case a userTemporaryToken with its id doesn't exist
     */
    UserTemporaryToken getTemporaryToken(Long id) throws ResourceNotFoundException;

    /**
     * Gets a specific userTemporaryToken filtering by token
     *
     * @param token the token of the userTemporaryToken
     * @return an optional reference for the userTemporaryToken
     */
    Optional<UserTemporaryToken> getTemporaryTokenByToken(String token);

    /**
     * deletes a passwordToken
     *
     * @param userTemporaryToken the userTemporaryToken to be deleted
     * @throws ResourceNotFoundException in case a passwordToken with its id doesn't exist
     */
    void deleteTemporaryToken(UserTemporaryToken userTemporaryToken) throws ResourceNotFoundException;

}
