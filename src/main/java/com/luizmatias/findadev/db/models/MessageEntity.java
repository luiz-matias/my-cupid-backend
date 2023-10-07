package com.luizmatias.findadev.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chatEntity;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private UserEntity fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private UserEntity toUser;
    private String message;
    private Date sentAt;

}
