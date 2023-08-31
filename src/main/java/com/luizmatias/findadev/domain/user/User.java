package com.luizmatias.findadev.domain.user;

import com.luizmatias.findadev.domain.address.Address;
import com.luizmatias.findadev.domain.match.Match;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private LocalDate birth;
    private String email;
    private String password;
    private UserType userType;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "from_user_id"),
            inverseJoinColumns = @JoinColumn(name = "to_user_id")
    )
    private List<User> likedUsers;
    @OneToMany(mappedBy = "clientUser")
    private List<Match> matchesAsClient;
    @OneToMany(mappedBy = "developerUser")
    private List<Match> matchesAsDeveloper;

}
