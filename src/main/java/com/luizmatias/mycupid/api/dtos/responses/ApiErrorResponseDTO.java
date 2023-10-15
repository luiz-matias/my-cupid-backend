package com.luizmatias.mycupid.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponseDTO {

    private Date timestamp;
    private int status;
    private String error;
    private Object details;

    public ApiErrorResponseDTO(Date timestamp, int status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }
}
