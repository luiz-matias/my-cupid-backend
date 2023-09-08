package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.AddressEntity;
import com.luizmatias.findadev.domain.entities.Address;

public class AddressEntityMapper {

    public static Address toAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getId(),
                addressEntity.getAddressLine1(),
                addressEntity.getAddressLine2(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getPostalCode(),
                addressEntity.getCountry(),
                addressEntity.getLatitude(),
                addressEntity.getLongitude()
        );
    }

    public static AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getId(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry(),
                address.getLatitude(),
                address.getLongitude()
        );
    }

}
