package com.luizmatias.findadev.domain.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Match {
    private Long id;
    private User clientUser;
    private User developerUser;
    private Date matchedAt;
    private Date unmatchedAt;

}
