package com.web.metrica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseExceptionDTO {


    private String message;
    private int status;


    public ResponseExceptionDTO(String message, int status) {
        this.status = status;
        this.message = message;
    }
}
