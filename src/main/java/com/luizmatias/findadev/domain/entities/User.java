package com.luizmatias.findadev.domain.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private LocalDate birth;
    private String email;
    private String password;
    private UserType userType;
    private Address address;
    private List<User> likedUsers;
    private List<Match> matchesAsClient;
    private List<Match> matchesAsDeveloper;

}
