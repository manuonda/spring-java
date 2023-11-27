package com.project.two.email.business.service;

import com.project.two.email.entity.request.RequestEmailDTO;
import com.project.two.email.presentation.controller.SendEmailController;
import org.apache.coyote.Request;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmailService {
    void sendEmailSimple(RequestEmailDTO requestEmailDTO);
    void sendMimeMessageWithAttachments(RequestEmailDTO requestEmailDTO , MultipartFile file);

    void sendMessageWithAttachmentsTemplate(RequestEmailDTO requestEmailDTO, MultipartFile file );
}
