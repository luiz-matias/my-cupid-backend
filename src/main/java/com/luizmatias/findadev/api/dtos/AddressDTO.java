package com.luizmatias.findadev.api.dtos;

public record AddressDTO(
        Long id,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        Double latitude,
        Double longitude
) {

}
