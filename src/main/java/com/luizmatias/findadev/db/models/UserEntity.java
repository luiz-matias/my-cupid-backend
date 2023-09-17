package com.luizmatias.findadev.db.models;

import com.luizmatias.findadev.domain.entities.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 500)
    private String bio;
    @Column(nullable = false)
    private Date birth;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private UserType userType;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "from_user_id"),
            inverseJoinColumns = @JoinColumn(name = "to_user_id")
    )
    private List<UserEntity> likedUserEntities;
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "to_user_id"),
            inverseJoinColumns = @JoinColumn(name = "from_user_id")
    )
    private List<UserEntity> likedByUserEntities;
    @OneToMany(mappedBy = "clientUserEntity", cascade = CascadeType.ALL)
    private List<MatchEntity> matchesAsClient;
    @OneToMany(mappedBy = "developerUserEntity", cascade = CascadeType.ALL)
    private List<MatchEntity> matchesAsDeveloper;
}
