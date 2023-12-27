package com.docker.kubernetes.curso.presentation.advice;


import com.docker.kubernetes.curso.domain.dto.ResponseExceptionDTO;
import com.docker.kubernetes.curso.domain.dto.ValidationErrorResponseDTO;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException {

    @ExceptionHandler(EntityFound.class)
    public ResponseEntity<?> handleEntityFound(EntityFound e){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFound e){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        ValidationErrorResponseDTO dto = new ValidationErrorResponseDTO();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            dto.addError(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
    }

}
