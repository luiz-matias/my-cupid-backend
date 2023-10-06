package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    /**
     * Creates a user and returns its own reference
     *
     * @param user the user to be created
     * @return user reference that was just created
     */
    User createUser(User user);

    /**
     * Gets all users
     *
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Gets a specific user filtering by id
     *
     * @param id the id of the user
     * @return a reference for the user
     * @throws ResourceNotFoundException in case a user with its id doesn't exist
     */
    User getUser(Long id) throws ResourceNotFoundException;

    /**
     * Gets a specific user filtering by email
     *
     * @param email the email of the user
     * @return a reference for the user
     */
    Optional<User> getUserByEmail(String email);

    /**
     * updates the user and returns its own reference
     *
     * @param user the data to be updated
     * @return the updated user
     * @throws ResourceNotFoundException in case a user with its id doesn't exist
     */
    User updateUserProfile(User user) throws ResourceNotFoundException;

    /**
     * updates the user likes and returns its own reference
     *
     * @param user the data to be updated
     * @return the updated user
     * @throws ResourceNotFoundException in case a user with its id doesn't exist
     */
    User updateUserLikes(User user) throws ResourceNotFoundException;

    /**
     * updates the user password and returns its own reference
     *
     * @param id       id of the user
     * @param password the updated password
     * @return the updated user
     * @throws ResourceNotFoundException in case a user with its id doesn't exist
     */
    User updateUserPassword(Long id, String password) throws ResourceNotFoundException;

    /**
     * deletes an user
     *
     * @param user the user to be deleted
     * @throws ResourceNotFoundException in case a user with its id doesn't exist
     */
    void deleteUser(User user) throws ResourceNotFoundException;

}
