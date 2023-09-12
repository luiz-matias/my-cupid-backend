package com.luizmatias.findadev.domain.usecases.address;

import com.luizmatias.findadev.domain.entities.Address;
import com.luizmatias.findadev.domain.repositories.AddressRepository;

import java.util.Optional;

public class GetAddressInteractor {

    private final AddressRepository addressRepository;

    public GetAddressInteractor(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    Optional<Address> getAddress(Long id) {
        return addressRepository.getAddress(id);
    }

}
