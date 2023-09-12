package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.api.dtos.AddressDTO;
import com.luizmatias.findadev.domain.entities.Address;

public class AddressDTOMapper {

    public static Address toAddress(AddressDTO addressDTO) {
        return new Address(
                addressDTO.id(),
                addressDTO.addressLine1(),
                addressDTO.addressLine2(),
                addressDTO.city(),
                addressDTO.state(),
                addressDTO.postalCode(),
                addressDTO.country(),
                addressDTO.latitude(),
                addressDTO.longitude()
        );
    }

    public static AddressDTO toAddressDTO(Address address) {
        return new AddressDTO(
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
