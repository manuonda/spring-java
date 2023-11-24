package com.project.two.email.business.service.impl;


import com.project.two.commons.dto.EmailEventDTO;
import com.project.two.email.business.service.EmailLogoService;
import com.project.two.email.entity.dto.EmailLoggerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class EmailListener {

    private static final Logger logger = LoggerFactory.getLogger(EmailListener.class);

    private final EmailLogoService emailLogService;

    public EmailListener(EmailLogoService emailLogService) {
        this.emailLogService = emailLogService;
    }

    @KafkaListener(id = "id",
     groupId = "group_envio_email",
     topics={"${spring.kafka.topic.envio_email_producto}"})
     public void emailListener(EmailEventDTO emailEventDTO) {

        // save logo
        EmailLoggerDTO emailLogDTO = new EmailLoggerDTO(
                emailEventDTO.to(),
                emailEventDTO.subject(),
                emailEventDTO.body()
        );
        this.emailLogService.saveEmailLogo(emailLogDTO);

        //send email
     }
}
