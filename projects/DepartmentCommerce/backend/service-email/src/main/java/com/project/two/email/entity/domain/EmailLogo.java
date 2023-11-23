package com.project.two.email.entity.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tbl_email_logs")
public record EmailLogo(
        @Id
        String id,
        String toAddress,
        String subject,
        String body
){
}

