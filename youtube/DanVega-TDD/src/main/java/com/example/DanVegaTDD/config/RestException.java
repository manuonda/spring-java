package com.example.DanVegaTDD.config;

import com.example.DanVegaTDD.domain.ErrorDTO;
import com.example.DanVegaTDD.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestException {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDTO> handlerNotFound(AppException ex){
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDTO(ex.getStatus().value(), ex.getMessage()));
    }
}
