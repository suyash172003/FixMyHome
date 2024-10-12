package com.example.FixMyHome.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	public void sendEmailTo(String recipeint,String message) {
		SimpleMailMessage obj=new SimpleMailMessage();
		obj.setFrom(username);
		obj.setTo(recipeint);
		obj.setText(message);
		sender.send(obj);
	}

}
