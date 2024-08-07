package com.tia102g1.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class MailController {

	    @Autowired
	    private MailService mailService;
	    
	    @GetMapping("/sendMail")
	    public String sendMail() {
	        mailService.sendPlainText("j05011993@hotmail.com.tw", "訂單成立", "您好，您的訂單已成立");
	        return "reidrect:ao6";
	    }
	}
