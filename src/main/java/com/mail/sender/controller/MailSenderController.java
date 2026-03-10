package com.mail.sender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mail.sender.service.MailSenderService;

@RestController
public class MailSenderController {

	private MailSenderService service;
	
	public MailSenderController(MailSenderService service) {
		this.service = service;
	}
	
	//@CrossOrigin(origins = "url")
	@PostMapping("/send")
	public ResponseEntity<String> getResumeAndSendMail(
			@RequestParam String name, @RequestParam String email,
			@RequestParam MultipartFile resume){
		System.out.println(resume.getOriginalFilename());
		String res = service.sendMail(name, email, resume);
		if(res.equals("Thank you for contacting")) {
			return ResponseEntity.ok("Thank you for contacting");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}

}
