package com.luizmatias.findadev.api.dtos.responses;

import java.util.Date;
import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String bio,
        Date birth,
        String email,
        String password,
        String userType,
        String userRole,
        Double latitude,
        Double longitude,
        List<UserDTO> likedUsers,
        List<UserDTO> likedByUsers,
        List<MatchDTO> matchesAsClient,
        List<MatchDTO> matchesAsDeveloper,
        List<InterestDTO> interests
) {

}