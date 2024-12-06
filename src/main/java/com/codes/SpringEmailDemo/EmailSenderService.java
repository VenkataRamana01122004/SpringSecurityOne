package com.codes.SpringEmailDemo;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String email,String sub,String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("venkataramanavegulla2004@gmail.com");
		message.setTo(email);
		message.setText(body);
		message.setSubject(sub);
		mailSender.send(message);
		
		System.out.println("text mail sent success");
	}
	
	public void sendMailAttachment()
	{
		System.out.println("mail");
		try
		{
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			
			helper.setFrom("venkataramanavegulla2004@gmail.com");
//			helper.setTo("sriram333ram@gmail.com");
			helper.setTo("venkataramanavegulla2004@gmail.com");
			helper.setText("body");
			helper.setSubject("sub");
			
			helper.addAttachment("moon", new File("D:\\Data\\moon.jpg"));
			
			mailSender.send(message);
			
			System.out.println("Attachment mail sent success");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	
}
