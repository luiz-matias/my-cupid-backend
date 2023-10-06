package com.luizmatias.findadev.db.models;

import com.luizmatias.findadev.domain.entities.UserRole;
import com.luizmatias.findadev.domain.entities.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

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
    private UserRole userRole;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @Column(nullable = false)
    private Boolean isEmailVerified;
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
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<UserTemporaryTokenEntity> passwordTokenEntities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(UserRole.USER.getRole()));

        if (userRole == UserRole.ADMIN) {
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
        }

        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
