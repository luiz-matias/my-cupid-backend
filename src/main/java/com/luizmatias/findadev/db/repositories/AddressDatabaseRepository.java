package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.AddressEntity;
import com.luizmatias.findadev.db.models.mappers.AddressEntityMapper;
import com.luizmatias.findadev.domain.entities.Address;
import com.luizmatias.findadev.domain.repositories.AddressRepository;

import java.util.Optional;

public class AddressDatabaseRepository implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    public AddressDatabaseRepository(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    @Override
    public Address createAddress(Address address) {
        AddressEntity addressEntity = AddressEntityMapper.toAddressEntity(address);
        return AddressEntityMapper.toAddress(addressJpaRepository.save(addressEntity));
    }

    @Override
    public Optional<Address> getAddress(Long id) {
        return addressJpaRepository.findById(id).map(AddressEntityMapper::toAddress);
    }

    @Override
    public Address updateAddress(Address address) {
        AddressEntity addressEntity = AddressEntityMapper.toAddressEntity(address);
        return AddressEntityMapper.toAddress(addressJpaRepository.save(addressEntity));
    }

    @Override
    public void deleteAddress(Address address) {
        addressJpaRepository.deleteById(address.getId());
    }

}
