package com.project.two.files.business.service;

import com.project.two.files.presentation.advice.FileDownloadException;
import com.project.two.files.presentation.advice.FileEmptyException;
import com.project.two.files.presentation.advice.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile multipartFile) throws FileUploadException, IOException;

    Object downloadFile(String fileName) throws FileDownloadException, IOException;

    boolean delete(String fileName) throws FileEmptyException;
}
