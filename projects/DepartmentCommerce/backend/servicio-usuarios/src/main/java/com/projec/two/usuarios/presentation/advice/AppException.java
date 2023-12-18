package com.projec.two.usuarios.presentation.advice;


import com.projec.two.usuarios.domain.dto.ResponseExceptionDTO;
import com.projec.two.usuarios.domain.dto.ValidationErrorResponseDTO;
import feign.Response;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException {


    @ExceptionHandler(EntityFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> handleEntityFoundException(EntityFoundException ex) {
        ResponseExceptionDTO response =
                new ResponseExceptionDTO(ex.getMessage(),HttpStatus.BAD_GATEWAY.value());
        return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        ResponseExceptionDTO response =
                new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        ValidationErrorResponseDTO validationErrorResponseDTO = new ValidationErrorResponseDTO();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrorResponseDTO.addError(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(validationErrorResponseDTO);
    }


}
