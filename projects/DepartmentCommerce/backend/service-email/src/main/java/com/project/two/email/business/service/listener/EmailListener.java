package com.project.two.email.business.service.listener;


import com.project.two.email.business.service.EmailService;
import com.project.two.commons.dto.RequestEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailListener {

   private Logger logger = LoggerFactory.getLogger(EmailListener.class);
   private final EmailService emailService;

   public EmailListener(EmailService emailService) {
      this.emailService = emailService;
   }

   @KafkaListener(id = "listener_email",
            groupId = "${spring.kafka.group}" ,
            topics = {"${spring.kafka.topic.envio_email}"})
   public void envioEmail(Object object) {
      logger.info("envio email ");
      RequestEmailDTO  requestEmailDTO  = (RequestEmailDTO) object;
      this.emailService.sendMessageWithAttachmentsTemplate(requestEmailDTO, null);
   }

}
