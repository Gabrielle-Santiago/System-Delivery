package com.gabrielle.delivery.email.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielle.delivery.authentication.user.User;
import com.gabrielle.delivery.email.service.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> SendEmailSuccess(@RequestBody User email) {
        emailService.sendEmail(email);
        return ResponseEntity.ok("Email sent successfully!");
    }
}
