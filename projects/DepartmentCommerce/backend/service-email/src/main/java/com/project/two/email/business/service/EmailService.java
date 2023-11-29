package com.project.two.email.business.service;

import com.project.two.commons.dto.RequestEmailDTO;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    void sendEmailSimple(RequestEmailDTO requestEmailDTO);
    void sendMimeMessageWithAttachments(RequestEmailDTO requestEmailDTO , MultipartFile file);

    void sendMessageWithAttachmentsTemplate(RequestEmailDTO requestEmailDTO, MultipartFile file );
}
