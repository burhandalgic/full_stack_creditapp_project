package com.burhan.creditmanagement.aplication.business.services.internal;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailManager implements SendEmailService{

    private MailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String text, String logStatus) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("burhan.dalkic@dizgemekatronik.com");  // Gönderen e-posta adresi
        message.setTo(to);                          // Alıcı e-posta adresi
        message.setSubject(subject);                // Konu
        message.setText(text);                      // E-posta içeriği

        mailSender.send(message);                   // E-postayı gönder
    }
}
