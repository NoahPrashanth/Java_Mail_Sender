package com.mail.sender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailSenderService {
	
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String to;

	public MailSenderService(JavaMailSender sender) {
		this.sender = sender;
	}
	
	public String sendMail(String name, String email, MultipartFile resume) {
		try {
			System.out.println(to);
			MimeMessage mail = sender.createMimeMessage();
			System.out.println(mail +" executed-1");
			MimeMessageHelper mailSender = new MimeMessageHelper(mail, true);
			System.out.println(mailSender + " executer-2");
			mailSender.setFrom(email.split(",")[0].trim());
			System.out.println("setting from "+ email);
			mailSender.setTo(to);
			System.out.println("setting to "+to);
			mailSender.setSubject("Application request from "+name);
			System.out.println("setting subject ");
			mailSender.addAttachment(resume.getOriginalFilename(), new ByteArrayResource(resume.getBytes()));
			System.out.println("Attached file");
			//System.out.println(mailSender.isValidateAddresses());
			sender.send(mail);
			System.out.println("sent");
			return "Thank you for contacting";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Something went wrong";
		}
	}

}
