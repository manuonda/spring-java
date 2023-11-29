package com.project.two.email.business.service.impl;

import com.project.two.email.business.service.EmailService;
import com.project.two.commons.dto.RequestEmailDTO;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private  JavaMailSender emailSender;

    @Value("${spring.mail.direction}")
    private String fromEmail;



    private final TemplateEngine templateEngine;

    public EmailServiceImpl(TemplateEngine templateEngine) {
      this.templateEngine = templateEngine;
    }


    @Override
    public void sendEmailSimple(RequestEmailDTO requestEmailDTO) {
      try {
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setFrom("manuonda@gmail.com");
          simpleMailMessage.setTo(requestEmailDTO.to());
          simpleMailMessage.setSubject(requestEmailDTO.subject());
          simpleMailMessage.setText(requestEmailDTO.body());
          emailSender.send(simpleMailMessage);
      }catch (Exception ex){
        System.out.println("Exception : " +ex.getMessage());
      }
    }

    @Override
    public void sendMimeMessageWithAttachments(RequestEmailDTO requestEmailDTO, MultipartFile file) {

          try {
              MimeMessage mimeMessage = this.emailSender.createMimeMessage();
              MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
              helper.setTo(requestEmailDTO.to());
              helper.setText(requestEmailDTO.body());
              helper.setSubject(requestEmailDTO.subject());
              helper.setFrom(fromEmail);
              if ( file != null ) {
                  helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
              }
              emailSender.send(mimeMessage);
              logger.info("Send email");
          }catch (Exception ex) {
             logger.error("Error send email with attachments files : " +  ex.getMessage());
          }

    }

    @Override
    public void sendMessageWithAttachmentsTemplate(RequestEmailDTO requestEmailDTO, MultipartFile file) {
        try {
            Context context = new Context();
            context.setVariable("body", requestEmailDTO.body());
            MimeMessage mimeMessage = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage ,true);
            String html = this.templateEngine.process("email-template.html", context);
            helper.setFrom(fromEmail);
            helper.setTo(requestEmailDTO.to());
            helper.setSubject(requestEmailDTO.subject());
            helper.setText(html, true);

            if( file != null ) {
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }
            this.emailSender.send(mimeMessage);
            logger.info("Send email with html and files");

        } catch(Exception ex ) {
            logger.error("Error send email template " + ex.getMessage());
            System.out.println("Error template : " + ex.getMessage());
        }
    }


}
