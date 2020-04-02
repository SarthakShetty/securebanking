package com.group12.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.group12.utils.MailEncryptionUtil;

@Component
public class EmailSigningService {
	
	@Autowired
	private JavaMailSender mailSender;

public void signAndSend(String toEmail, String subject, String message) throws Exception {
	
	MimeMessage msg = mailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(msg);
	helper.setTo(toEmail);
	helper.setFrom("group12banker@gmail.com");
	helper.setSubject(subject);
	helper.setText(message, true);
	
	mailSender.send(MailEncryptionUtil.signMessage(msg));
	
}
}