package com.project.two.email.entity.request;


import lombok.Data;
import lombok.ToString;


public record RequestEmailDTO(
        String to,
        String subject,
        String body
) {
}
