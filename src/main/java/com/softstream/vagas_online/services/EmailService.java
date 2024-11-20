package com.softstream.vagas_online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.softstream.vagas_online.services.exceptions.EmailException;

@Service
public class EmailService {
	
	@Value("spring.mail.username")
	private String emailFrom;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail(String to, String subject, String body) {
		try {
			SimpleMailMessage messege = new SimpleMailMessage();
			messege.setFrom(emailFrom);
			messege.setTo(to);
			messege.setSubject(subject);
			messege.setText(body);
			emailSender.send(messege);
		} catch (MailException e) {
			throw new EmailException("Failed to send email");
		}
	}

}
