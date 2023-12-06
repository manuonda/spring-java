package com.project.two.files.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.finspacedata.model.AwsCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3EncryptionClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Configuration AWS
 */
@Configuration
public class AmazonS3Config {

      @Value("${aws.secret.key}")
      private String secretKey;

      @Value("${aws.secret.value}")
      private String secretValue;

      @Value("${aws.region}")
      private String region;

      @Value("${aws.bucket.url}")
      private String bucketUrl;

      @Value("${aws.bucket.name}")
      private String bucketName;


      @Bean
      public AmazonS3 client(){
            BasicAWSCredentials credentials = new BasicAWSCredentials( secretKey, secretValue);

           return AmazonS3ClientBuilder.standard()
                   .withCredentials(new AWSStaticCredentialsProvider(credentials))
                   .withRegion(region)
                   .build();
      }
}
