package com.project.two.email.entity.request;


public record RequestEmailDTO(
        String to,
        String subject,
        String body
) {
}
