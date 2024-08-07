package com.tia102g1.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
public void sendPlainText(String receivers, String subject, String content) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setTo("j05011993@hotmail.com.tw");
	message.setSubject("訂單成立");
	message.setText("您好，您的訂單已成立");
	
	mailSender.send(message);
	
}
}