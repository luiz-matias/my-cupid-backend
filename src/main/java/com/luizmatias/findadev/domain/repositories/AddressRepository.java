package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.Address;

import java.util.Optional;

public interface AddressRepository {

    /**
     * Creates a user and returns its own reference
     *
     * @param address the Address to be created
     * @return Address reference that was just created
     */
    Address createAddress(Address address);

    /**
     * Gets a specific address filtering by id
     *
     * @param id the id of the address
     * @return an optional reference for the address in case an address with its id doesn't exist
     */
    Optional<Address> getAddress(Long id);

    /**
     * updates the address and returns its own reference
     *
     * @param address the data to be updated
     * @return the updated address
     */
    Address updateAddress(Address address);

    /**
     * deletes an address
     *
     * @param address the address to be deleted
     */
    void deleteAddress(Address address);

}
