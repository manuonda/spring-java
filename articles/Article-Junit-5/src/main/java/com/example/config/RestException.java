package com.example.config;


import com.example.dto.ErrorDTO;
import com.example.exception.AppException;
import com.example.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestException {

    @ExceptionHandler(value = { EmployeeNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDTO(ex.getMessage(),ex.getStatus().value(), new Date()));
    }

    @ExceptionHandler(value = { AppException.class})
    public ResponseEntity<ErrorDTO> handleAppException(AppException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDTO(ex.getMessage(),ex.getStatus().value(),new Date()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
       Map<String, String> errorMap = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach( error -> {
           errorMap.put(error.getField(), error.getDefaultMessage());
       });
       return errorMap;
    }


}
