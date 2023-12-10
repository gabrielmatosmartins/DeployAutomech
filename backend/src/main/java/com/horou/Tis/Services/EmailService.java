package com.horou.Tis.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

@Autowired    
private JavaMailSender emailSender;

@Value("${support.mail}")
private String supportMail;

public void sendEmailToClient(String subject, String email, String content) throws MessagingException{
    MimeMessage mail = emailSender.createMimeMessage();

    MimeMessageHelper message = new MimeMessageHelper(mail);
    message.setSubject(subject);
    message.setText(content,true);
    message.setFrom(supportMail);
    message.setTo(email);
}


public String getContentMailCertificate(String name, String emailcliente){

    return "<p>Ol&aacute;,"  + name +    "! Seu servi&ccedil;o acaba de ser de ter o status atualizado.</p>"
    + "<p>Confira o Status:</p>" + "<p>" + emailcliente + "</p>";

}




}