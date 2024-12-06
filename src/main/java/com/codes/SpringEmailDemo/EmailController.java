package com.codes.SpringEmailDemo;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class EmailController {

    private  JavaMailSender mailSender;
    
    @Value("${spring.mail.username}") 
    private String senderEmail;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("tutorial.genuinecoder@gmail.com");
            message.setTo("tutorial.genuinecoder@gmail.com");
            message.setSubject("Simple test email from GC!");
            message.setText("This is a sample email body for my first email!");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String sendEmailWithAttachment() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("tutorial.genuinecoder@gmail.com");
            helper.setTo("tutorial.genuinecoder@gmail.com");
            helper.setSubject("Java email with attachment | From GC");
            helper.setText("Please find the attached documents below");

            helper.addAttachment("logo.png", new File("C:\\Users\\Genuine Coder\\Documents\\Attachments\\logo.png"));
            helper.addAttachment("presentation.pptx", new File("C:\\Users\\Genuine Coder\\Documents\\Attachments\\presentation.pptx"));

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void sendHtmlEmail() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

//            System.out.println(senderEmail);
//            helper.setFrom(senderEmail);
            helper.setFrom("venkataramanavegulla2004@gmail.com");
            helper.setTo("sriram333ram@gmail.com");
            helper.setSubject("Java email with attachment | From GC");

            try (var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/email-content.html")))
            {
                helper.setText(
                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
                        true
                );
            }
            helper.addInline("logo.png", new File("D:\\Data\\moon.jpg"));
            mailSender.send(message);
//            return "success!";
            System.out.println("Success");
        } catch (Exception e) {
//            return e.getMessage();
        	System.out.println(e.getMessage());
        }
    }
}
