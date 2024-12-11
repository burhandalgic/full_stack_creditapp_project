package com.burhan.creditmanagement.aplication.business.services;

import com.burhan.creditmanagement.aplication.business.services.internal.SendEmailManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendEmailManagerTest {

    @Mock
    MailSender mailSender;
    @InjectMocks
    SendEmailManager sendEmailManager;

    @Test
    void sendEmail() {
        sendEmailManager.sendEmail("","","","");
        verify(mailSender,times(1)).send(any(SimpleMailMessage.class));
    }
}