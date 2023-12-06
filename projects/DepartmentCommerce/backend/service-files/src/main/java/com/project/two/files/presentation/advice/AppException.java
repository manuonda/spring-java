package com.project.two.files.presentation.advice;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException extends RuntimeException{


    @ExceptionHandler

}
