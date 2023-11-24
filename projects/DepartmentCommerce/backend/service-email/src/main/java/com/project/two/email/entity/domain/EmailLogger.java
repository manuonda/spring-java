package com.project.two.email.entity.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tbl_email_logger")
public record EmailLogger(
        @Id
        String id,
        String toAddress,
        String subject,
        String body
){
}

