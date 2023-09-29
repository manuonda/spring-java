package com.example.config;


import com.example.dto.ErrorDTO;
import com.example.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestException {

    @ExceptionHandler(value = { EmployeeNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDTO(ex.getMessage(),ex.getStatus().value(), new Date()));
    }


}
