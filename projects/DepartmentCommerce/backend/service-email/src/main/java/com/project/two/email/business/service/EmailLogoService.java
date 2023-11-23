package com.project.two.email.business.service;

import com.project.two.email.entity.dto.EmailLogDTO;

public interface EmailLogoService {

    EmailLogDTO saveEmailLogo(EmailLogDTO emailLogDTO);

    void sendEmail(String toAddress, String subject, String body );
}
