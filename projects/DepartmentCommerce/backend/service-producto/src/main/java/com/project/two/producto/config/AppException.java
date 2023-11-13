package com.project.two.producto.config;


import com.project.two.producto.dto.ResponseExceptionDTO;
import com.project.two.producto.dto.ValidationErrorResponseDTO;
import com.project.two.producto.exception.EntityExists;
import com.project.two.producto.exception.EntityNotFound;
import org.apache.coyote.Response;
import org.flywaydb.core.api.ErrorDetails;
import org.hibernate.annotations.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppException {

    private static Logger logger = LoggerFactory.getLogger(AppException.class);

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDTO> entityNotFound(EntityNotFound ex) {
      logger.info("Error handle Not Found , {}", ex.getMessage());
      ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(EntityExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseExceptionDTO> entityExists(EntityExists ex) {
        logger.info("Error handler Exists ", ex.getMessage());
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {

       ValidationErrorResponseDTO response =new ValidationErrorResponseDTO();

       ex.getBindingResult().getFieldErrors().forEach(error->{
            response.addError(error.getField(), error.getDefaultMessage());
       });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
