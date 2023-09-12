package com.luizmatias.findadev.domain.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Match {
    private Long id;
    private User clientUser;
    private User developerUser;
    private LocalDateTime matchedAt;
    private LocalDateTime unmatchedAt;

}
