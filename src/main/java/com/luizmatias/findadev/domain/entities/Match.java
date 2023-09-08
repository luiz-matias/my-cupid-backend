package com.luizmatias.findadev.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private Long id;
    private User clientUser;
    private User developerUser;
    private LocalDateTime matchedAt;
    private LocalDateTime unmatchedAt;

}
