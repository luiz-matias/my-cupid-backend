package com.luizmatias.findadev.api.dtos;

import java.time.LocalDate;
import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String bio,
        LocalDate birth,
        String email,
        String password,
        UserTypeDTO userType,
        AddressDTO address,
        List<UserDTO> likedUsers,
        List<MatchDTO> matchesAsClient,
        List<MatchDTO> matchesAsDeveloper
) {

}