package com.luizmatias.findadev.domain.usecases.address;

import com.luizmatias.findadev.domain.entities.Address;
import com.luizmatias.findadev.domain.repositories.AddressRepository;

public class CreateAddressInteractor {

    private final AddressRepository addressRepository;

    public CreateAddressInteractor(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    Address createAddress(Address address) {
        return addressRepository.createAddress(address);
    }

}
