package com.burhan.creditmanagement.aplication.business.services.internal;

public interface SendEmailService {
    void sendEmail(String to, String subject, String text, String logStatus);
}
