package com.web.metrica.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
