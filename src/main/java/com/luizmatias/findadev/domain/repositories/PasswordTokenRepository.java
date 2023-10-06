package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.PasswordToken;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;

import java.util.Optional;

public interface PasswordTokenRepository {

    /**
     * Creates a passwordToken and returns its own reference
     *
     * @param passwordToken the passwordToken to be created
     * @return passwordToken reference that was just created
     */
    PasswordToken createPasswordToken(PasswordToken passwordToken);

    /**
     * Gets a specific passwordToken filtering by id
     *
     * @param id the id of the passwordToken
     * @return a reference for the passwordToken
     * @throws ResourceNotFoundException in case a passwordToken with its id doesn't exist
     */
    PasswordToken getPasswordToken(Long id) throws ResourceNotFoundException;

    /**
     * Gets a specific passwordToken filtering by token
     *
     * @param token the token of the passwordToken
     * @return an optional reference for the passwordToken
     */
    Optional<PasswordToken> getPasswordTokenByToken(String token);

    /**
     * deletes a passwordToken
     *
     * @param passwordToken the passwordToken to be deleted
     * @throws ResourceNotFoundException in case a passwordToken with its id doesn't exist
     */
    void deletePasswordToken(PasswordToken passwordToken) throws ResourceNotFoundException;

}
