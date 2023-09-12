package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.User;

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
     * @return an optional reference for the user in case a user with its id doesn't exist
     */
    Optional<User> getUser(Long id);

    /**
     * updates the user and returns its own reference
     *
     * @param id   the id of the user to be updated
     * @param user the data to be updated
     * @return the updated user
     */
    User updateUser(User user);

    /**
     * deletes an user
     *
     * @param user the user to be deleted
     */
    void deleteUser(User user);

}
