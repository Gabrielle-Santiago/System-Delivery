package com.gabrielle.delivery.service;

import com.gabrielle.delivery.authentication.user.User;
import com.gabrielle.delivery.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService service;

    @Mock
    private JavaMailSender emailSender;

    @Test
    void sendEmail() {
        User user = new User();
        user.setEmail("test@email.com");

        service.sendEmail(user);

        verify(emailSender).send(any(SimpleMailMessage.class));
    }
}
