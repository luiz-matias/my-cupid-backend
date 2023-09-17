package com.luizmatias.findadev.domain.entities;

import lombok.*;

import java.util.Date;
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
    private Date birth;
    private String email;
    private String password;
    private UserType userType;
    private Double latitude;
    private Double longitude;
    private List<User> likedUsers;
    private List<User> likedByUsers;
    private List<Match> matchesAsClient;
    private List<Match> matchesAsDeveloper;

}