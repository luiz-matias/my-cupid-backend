package com.luizmatias.findadev.domain.match;

import com.luizmatias.findadev.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User clientUser;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private User developerUser;
    private LocalDateTime matchedAt;
    private LocalDateTime unmatchedAt;

}
