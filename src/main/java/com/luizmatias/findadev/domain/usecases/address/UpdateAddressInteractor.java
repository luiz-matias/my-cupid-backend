package com.luizmatias.findadev.domain.usecases.address;

import com.luizmatias.findadev.domain.entities.Address;
import com.luizmatias.findadev.domain.repositories.AddressRepository;

public class UpdateAddressInteractor {

    private final AddressRepository addressRepository;

    public UpdateAddressInteractor(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    Address updateAddress(Address address) {
        return addressRepository.updateAddress(address);
    }

}
