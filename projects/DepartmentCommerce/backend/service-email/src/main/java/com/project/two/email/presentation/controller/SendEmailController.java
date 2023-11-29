package com.project.two.email.presentation.controller;


import com.project.two.email.business.service.EmailService;
import com.project.two.commons.dto.RequestEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/email")
public class SendEmailController {
    private static final Logger logger = LoggerFactory.getLogger(SendEmailController.class);

   private final EmailService emailService;

    public SendEmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send")
    public void sendEmail(@RequestBody RequestEmailDTO request){
        logger.info("RequestNotification DTO : ", request.toString());
        this.emailService.sendEmailSimple(request);
    }

    @PostMapping(value = "/sendEmailAttachment" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String sendEmailAttachment(RequestEmailDTO requestEmailDTO,
                                      MultipartFile file) {
        logger.info("RequestEmailDTO : "+requestEmailDTO.body()+"to: "+ requestEmailDTO.to()+requestEmailDTO.subject());
        logger.info("file " + file.getName()+ " inforacion " + file.getOriginalFilename());
        this.emailService.sendMimeMessageWithAttachments(requestEmailDTO, file);
        return "Send ";
    }

    @PostMapping(value = "/sendEmailFileHtml" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String sendEmailAttachmentFiles(RequestEmailDTO requestEmailDTO,
                                           MultipartFile file) {
        logger.info("Send Email File With Html");
        this.emailService.sendMessageWithAttachmentsTemplate(requestEmailDTO, file);
        return "Send";
    }
}
//read articule :

//https://stackabuse.com/guide-to-simple-email-service-aws-ses-with-spring-boot-and-spring-cloud/
//https://www.tutorialsbuddy.com/send-email-with-attachment-using-amazon-ses-in-java-spring-boot-example#gsc.tab=0
//https://www.youtube.com/watch?v=9HzFYYj-BuE&t=211s
//https://www.youtube.com/watch?v=skQxE6zx99M

//sigo con esto => https://www.youtube.com/watch?app=desktop&v=onCzCxDyR24
//sigo con esto => https://stackabuse.com/guide-to-simple-email-service-aws-ses-with-spring-boot-and-spring-cloud/