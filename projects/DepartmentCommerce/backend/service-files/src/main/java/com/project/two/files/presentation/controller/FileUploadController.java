package com.project.two.files.presentation.controller;


import com.amazonaws.services.apigatewayv2.model.Api;
import com.project.two.files.business.service.FileService;
import com.project.two.files.domain.APIResponse;
import com.project.two.files.presentation.advice.FileDownloadException;
import com.project.two.files.presentation.advice.FileEmptyException;
import com.project.two.files.presentation.advice.FileUploadException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Read Uplado S3
//https://www.twilio.com/blog/media-file-storage-java-spring-boot-amazon-s3-buckets
@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@Validated
public class FileUploadController {

    private final FileService fileService;


    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile multipartFile)
            throws FileEmptyException, IOException, FileUploadException {
        if(multipartFile.isEmpty()) {
            throw new FileEmptyException("File is empty. Cannot save empty file");
        }


        boolean isValidFile = isValidFile(multipartFile);
        List<String> allowedFileExtensions =  new ArrayList<>(Arrays.asList("pdf","txt","epub"));
        boolean isValidExtension = allowedFileExtensions.contains(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        if(isValidFile && isValidExtension) {
            String fileName = fileService.uploadFile(multipartFile);
            APIResponse apiResponse = APIResponse.builder()
                    .message("File upload successfully. File unique name: " + fileName)
                    .isSuccessful(true)
                    .statusCode(HttpStatus.OK.value())
                    .build();

            return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } else {
           APIResponse apiResponse = APIResponse.builder()
                   .message("Invalid File. File extension or File Name is not supported")
                   .isSuccessful(false)
                   .statusCode(HttpStatus.BAD_REQUEST.value())
                   .build();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

   }

   @GetMapping("/download")
   public ResponseEntity<?> downloadFile(@RequestParam("fileName")  String fileName) throws IOException, FileDownloadException {
       if (StringUtils.isEmpty(fileName)) {
           APIResponse apiResponse = APIResponse.builder()
                   .statusCode(HttpStatus.BAD_REQUEST.value())
                   .message("File not empty")
                   .isSuccessful(false)
                   .build();
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
       }
       Object response = this.fileService.downloadFile(fileName);
      if ( response != null) {
          return ResponseEntity.ok()
                  .header(HttpHeaders.CONTENT_DISPOSITION,
                          "attachment; filename=" + fileName)
                  .body(response);
      } else {
           APIResponse apiResponse = APIResponse.builder()
                   .statusCode(HttpStatus.NOT_FOUND.value())
                   .isSuccessful(false)
                   .message("Field could not be found")
                   .build();

           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
      }
   }


   @GetMapping("/delete")
   public ResponseEntity<?> deleteFile(@RequestParam("fileName") String fileName) throws FileDownloadException, FileEmptyException {

       if (StringUtils.isEmpty(fileName)) {
           throw new IllegalArgumentException("File name cannot be empty");
       }
       var isDelete =  this.fileService.delete(fileName);
       if ( isDelete ) {
           APIResponse apiResponse = APIResponse.builder()
                   .message("File delete  "+fileName)
                   .isSuccessful(true)
                   .statusCode(HttpStatus.OK.value())
                   .build();
           return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
       } else {
           APIResponse apiResponse = APIResponse.builder()
                   .statusCode(HttpStatus.NOT_FOUND.value())
                   .isSuccessful(false)
                   .message("File not found")
                   .build();
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
       }
   }

   private boolean isValidFile(MultipartFile multipartFile) {
       log.info("Empty status => {}", multipartFile.isEmpty());
       if(Objects.isNull(multipartFile.getOriginalFilename())) {
           return false;
       }
       return !multipartFile.getOriginalFilename().trim().equals("");

   }

 }
