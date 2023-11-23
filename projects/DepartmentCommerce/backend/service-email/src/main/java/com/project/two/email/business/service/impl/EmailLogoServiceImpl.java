package com.project.two.email.business.service.impl;

import com.project.two.email.business.mapper.EmailLogMapper;
import com.project.two.email.business.service.EmailLogoService;
import com.project.two.email.entity.domain.EmailLogo;
import com.project.two.email.entity.dto.EmailLogDTO;
import com.project.two.email.repository.EmailLogoRepository;
import org.springframework.stereotype.Service;


@Service
public class EmailLogoServiceImpl implements EmailLogoService {

    private final EmailLogoRepository emailLogoRepository;

    private final EmailLogMapper emailLogMapper;

    public EmailLogoServiceImpl(EmailLogoRepository emailLogoRepository, EmailLogMapper emailLogMapper) {
        this.emailLogoRepository = emailLogoRepository;
        this.emailLogMapper = emailLogMapper;
    }



    @Override
    public EmailLogDTO saveEmailLogo(EmailLogDTO emailLogDTO) {
        EmailLogo emailLogo = this.emailLogMapper.toEntity(emailLogDTO);
        EmailLogo saveEmailLogo = this.emailLogoRepository.save(emailLogo);
        return this.emailLogMapper.toDTO(saveEmailLogo);
    }

    @Override
    public void sendEmail(String toAddress, String subject, String body) {

    }
}
