package com.docker.kubernetes.usuarios.presentation.advice;


import com.docker.kubernetes.usuarios.domain.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException extends RuntimeException {


    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFound exception) {
        ResponseExceptionDTO exceptionDTO = new ResponseExceptionDTO(exception.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(EntityFound.class)
    public ResponseEntity<?> handleEntityxist(EntityFound exception) {
        ResponseExceptionDTO exceptionDTO = new ResponseExceptionDTO(exception.getMessage(),
                HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);
    }


}
