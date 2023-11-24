package com.project.two.email.presentation.controller;


import com.project.two.email.business.service.EmailLogoService;
import com.project.two.email.entity.request.RequestEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            @RequestBody RequestEmailDTO request
            ){
        logger.info("RequestNotification DTO : ", request.toString());

    }
}

//https://www.youtube.com/watch?v=9HzFYYj-BuE&t=211s
//https://www.youtube.com/watch?v=skQxE6zx99M
