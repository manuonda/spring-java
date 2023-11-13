package com.web.metrica.exception;


import com.web.metrica.dto.ResponseExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Maneja el control de exceptions a nivel global
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDTO> handleNotFound(EntityNotFoundException ex) {
        logger.info("handleNotFound " + ex.getMessage()  );
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseExceptionDTO> handleAlreadyExist(EntityAlreadyExistException ex) {
        logger.info("handlerAlreadyExist " + ex.getMessage());
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

}
