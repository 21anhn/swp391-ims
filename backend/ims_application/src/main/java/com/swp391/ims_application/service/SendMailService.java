package com.swp391.ims_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("$(spring.mail.username)")
    private String fromMailId;

    public void sendMail(String toMailId, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMailId);
        message.setTo(toMailId);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
