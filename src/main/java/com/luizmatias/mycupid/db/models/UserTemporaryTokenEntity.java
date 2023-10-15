package com.luizmatias.mycupid.db.models;

import com.luizmatias.mycupid.domain.entities.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_temporary_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTemporaryTokenEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    private String token;
    private TokenType tokenType;
    private Date expiresAt;

}
