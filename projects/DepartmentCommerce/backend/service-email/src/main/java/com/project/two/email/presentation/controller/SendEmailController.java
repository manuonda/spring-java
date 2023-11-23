package com.project.two.email.presentation.controller;


import com.project.two.email.business.service.EmailLogoService;
import jakarta.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class SendEmailController {
    private static final Logger logger = LoggerFactory.getLogger(SendEmailController.class);

   private final EmailLogoService emailLogService;


    public SendEmailController(EmailLogoService emailLogService) {
        this.emailLogService = emailLogService;
    }

    @GetMapping("/email")
    public void sendEmail(
            @PathParam("toAddress") String toAddress,
            @PathParam("subject") String subject,
            @PathParam("body") String body
      ){
       logger.info("Send Email : " + toAddress + " subject : "+ subject+ " body : " + body);
       this.emailLogService.
    }
}

//https://www.youtube.com/watch?v=9HzFYYj-BuE&t=211s
//https://www.youtube.com/watch?v=skQxE6zx99M
