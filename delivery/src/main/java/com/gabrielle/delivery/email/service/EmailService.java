package com.gabrielle.delivery.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gabrielle.delivery.authentication.user.User;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(User email){
        var message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email.getEmail());
        message.setSubject("New order placed!");
        message.setText("A new order has just been placed in our system. We recommend that you check all the order details right away to ensure the best possible tracking and processing. Check now!!");
        emailSender.send(message);
    }
}
