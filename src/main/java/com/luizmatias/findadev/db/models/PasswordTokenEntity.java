package com.luizmatias.findadev.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "password_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordTokenEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    private String token;
    private Date expiresAt;

}
