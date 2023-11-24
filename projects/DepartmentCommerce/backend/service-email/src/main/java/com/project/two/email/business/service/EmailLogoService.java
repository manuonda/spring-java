package com.project.two.email.business.service;

import com.project.two.email.entity.dto.EmailLoggerDTO;

public interface EmailLogoService {

    EmailLoggerDTO saveEmailLogo(EmailLoggerDTO emailLogDTO);

    void sendEmail(String toAddress, String subject, String body );
}
