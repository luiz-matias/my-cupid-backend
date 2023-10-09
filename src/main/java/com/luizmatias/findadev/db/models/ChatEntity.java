package com.luizmatias.findadev.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "first_user_id")
    private UserEntity firstUserEntity;
    @ManyToOne
    @JoinColumn(name = "second_user_id")
    private UserEntity secondUserEntity;
    private Date createdAt;

}
