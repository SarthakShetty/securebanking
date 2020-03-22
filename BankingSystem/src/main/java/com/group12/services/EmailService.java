package com.group12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

    public void sendMail(String toEmail, String subject, String message) {

    	SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("group12banker@gmail.com");

        javaMailSender.send(mailMessage);
    }

	
}
	

		
	   

	
