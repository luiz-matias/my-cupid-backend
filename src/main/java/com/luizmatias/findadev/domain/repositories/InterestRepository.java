package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.entities.pagination.PageRequest;
import com.luizmatias.findadev.domain.entities.pagination.PageResponse;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;

public interface InterestRepository {

    /**
     * Creates an interest and returns its own reference
     *
     * @param interest the interest to be created
     * @return interest reference that was just created
     */
    Interest createInterest(Interest interest);

    /**
     * Get an interest for a given id
     *
     * @param id the interest id
     * @return interest for the id
     * @throws ResourceNotFoundException in case an interest wasn't found for the given id
     */
    Interest getInterestById(Long id) throws ResourceNotFoundException;

    /**
     * Gets the list of interests
     *
     * @param pageRequest the pagination configuration
     * @return paginated response of chats for the given user
     */
    PageResponse<Interest> getAllInterests(PageRequest pageRequest);

    /**
     * Updates an interest
     *
     * @param interest the updated interest
     * @return the updated interest
     */
    Interest updateInterest(Interest interest) throws ResourceNotFoundException;

    /**
     * deletes an interest
     *
     * @param interest the interest to be deleted
     * @throws ResourceNotFoundException in case an interest with its id doesn't exist
     */
    void deleteInterest(Interest interest) throws ResourceNotFoundException;
}
