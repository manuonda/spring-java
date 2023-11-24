package com.project.two.email.business.service.impl;

import com.project.two.email.business.mapper.EmailLoggerMapper;
import com.project.two.email.business.service.EmailLogoService;
import com.project.two.email.entity.domain.EmailLogger;
import com.project.two.email.entity.dto.EmailLoggerDTO;
import com.project.two.email.repository.EmailLoggerRepository;
import org.springframework.stereotype.Service;


@Service
public class EmailLogoServiceImpl implements EmailLogoService {

    private final EmailLoggerRepository emailLogoRepository;

    private final EmailLoggerMapper emailLogMapper;

    public EmailLogoServiceImpl(EmailLoggerRepository emailLogoRepository, EmailLoggerMapper emailLogMapper) {
        this.emailLogoRepository = emailLogoRepository;
        this.emailLogMapper = emailLogMapper;
    }



    @Override
    public EmailLoggerDTO saveEmailLogo(EmailLoggerDTO emailLogDTO) {
        EmailLogger emailLogo = this.emailLogMapper.toEntity(emailLogDTO);
        EmailLogger saveEmailLogo = this.emailLogoRepository.save(emailLogo);
        return this.emailLogMapper.toDTO(saveEmailLogo);
    }

    @Override
    public void sendEmail(String toAddress, String subject, String body) {

    }
}
