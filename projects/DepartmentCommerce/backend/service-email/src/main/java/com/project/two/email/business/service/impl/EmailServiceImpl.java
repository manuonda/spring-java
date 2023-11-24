package com.project.two.email.business.service.impl;


import com.project.two.email.entity.request.RequestEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {


    @Autowired(required = false)
    private JavaMailSender javaMailSender;


    public void SendEmail(RequestEmailDTO requestEmailDTO) {
        try{

        }catch (Exception ex){
            
        }
    }


}
