package com.luizmatias.findadev.domain.usecases.address;

import com.luizmatias.findadev.domain.entities.Address;
import com.luizmatias.findadev.domain.repositories.AddressRepository;

public class DeleteAddressInteractor {

    private final AddressRepository addressRepository;

    public DeleteAddressInteractor(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    void deleteAddress(Address address) {
        addressRepository.deleteAddress(address);
    }

}
