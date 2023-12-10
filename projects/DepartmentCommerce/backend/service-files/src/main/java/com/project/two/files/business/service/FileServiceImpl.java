package com.project.two.files.business.service;

import com.amazonaws.services.backupstorage.model.DeleteObjectResult;
import com.amazonaws.services.codecommit.model.FileDoesNotExistException;
import com.amazonaws.services.neptunedata.model.S3Exception;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.project.two.files.presentation.advice.FileDownloadException;
import com.project.two.files.presentation.advice.FileEmptyException;
import com.project.two.files.presentation.advice.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    @Value("${aws.bucket.name}")
    private String bucketName;

    private AmazonS3 s3Client;


    @Override
    public Object downloadFile(String fileName) throws FileDownloadException, IOException {
        if(bucketIsEmpty()) {
            throw new FileDownloadException("Requested bucket do no existed or is empty");
        }

        S3Object object = s3Client.getObject(bucketName, fileName);
        try (S3ObjectInputStream s3is = object.getObjectContent()){
            try(FileOutputStream fos = new FileOutputStream(new File(fileName))){
                int len;
                byte[] buffer = new byte[1024];
                while ((len = s3is.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            }
            Path pathObject = Paths.get(fileName);
            Resource resource = new UrlResource(pathObject.toUri());
            if ( resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileDownloadException("File not found");
            }

        }
    }

    @Override
    public boolean delete(String fileName)  {

        if(!StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("FileName not empty");
        }
        DeleteObjectRequest deleteObjectRequest = new  DeleteObjectRequest(bucketName, fileName);
        try {
            s3Client.deleteObject(deleteObjectRequest);
            return true;
        } catch (S3Exception ex ) {
           return false;
        }


    }


    @Override
    public String uploadFile(MultipartFile multipartFile) throws FileUploadException, IOException {
        // convert multipartFile to file
        File file = new File(multipartFile.getOriginalFilename());
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        }

        // generate file name
        String fileName = generateFileName(multipartFile);

        // upload file
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("plaint/"+ FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        metadata.addUserMetadata("Title","File Upload - " + fileName);
        metadata.setContentLength(file.length());
        request.setMetadata(metadata);
        s3Client.putObject(request);
        return fileName;
    }

    private boolean bucketIsEmpty(){
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        if(result == null) {
            return false;
        }
        List<S3ObjectSummary> objects =  result.getObjectSummaries();
        return objects.isEmpty();
    }
    private String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime()+"-"+multipartFile.getOriginalFilename().replace(" ","-");
    }
}
