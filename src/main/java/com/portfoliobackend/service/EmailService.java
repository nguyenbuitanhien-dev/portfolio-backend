package com.portfoliobackend.service;

public interface EmailService {
    void sendContactNotification(String fromName, String fromEmail, String subject, String messageContent);
}
