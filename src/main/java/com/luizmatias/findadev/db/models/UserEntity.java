package com.luizmatias.findadev.db.models;

import com.luizmatias.findadev.domain.entities.UserType;
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
public class UserEntity {

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
    private AddressEntity addressEntity;
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "from_user_id"),
            inverseJoinColumns = @JoinColumn(name = "to_user_id")
    )
    private List<UserEntity> likedUserEntities;
    @OneToMany(mappedBy = "clientUserEntity")
    private List<MatchEntity> matchesAsClient;
    @OneToMany(mappedBy = "developerUserEntity")
    private List<MatchEntity> matchesAsDeveloper;

}
