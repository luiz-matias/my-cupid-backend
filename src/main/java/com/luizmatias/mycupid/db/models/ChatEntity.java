package com.luizmatias.mycupid.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "first_user_id")
    private UserEntity firstUserEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "second_user_id")
    private UserEntity secondUserEntity;
    private Date createdAt;

}
