package com.project.two.files.presentation.advice;

public class FileEmptyException extends SpringBootFileUploadException {

    public FileEmptyException(String message) {
        super(message);
    }
}
