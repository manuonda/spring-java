package com.project.two.commons.dto;


import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public record RequestEmailDTO(
        String to,
        String subject,
        String body,

        List<MultipartFile> list
) {
}
