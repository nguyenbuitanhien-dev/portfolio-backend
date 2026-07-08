package com.portfoliobackend.service.impl;

import com.portfoliobackend.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String mailFrom;

    @Value("${app.contact.notification-email:hiennguyenbuitan@gmail.com}")
    private String adminEmail;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendContactNotification(String fromName, String fromEmail, String subject, String messageContent) {
        try {
            if (mailFrom == null || mailFrom.isEmpty()) {
                System.out.println("Email sender address (spring.mail.username) is empty. Async email sending skipped.");
                return;
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mailFrom);
            mailMessage.setTo(adminEmail);
            mailMessage.setSubject("New Portfolio Contact: " + subject);
            mailMessage.setText(
                    "You have received a new contact message from your portfolio website:\n\n" +
                    "Sender Name: " + fromName + "\n" +
                    "Sender Email: " + fromEmail + "\n\n" +
                    "Subject: " + subject + "\n" +
                    "Message:\n" + messageContent
            );

            mailSender.send(mailMessage);
            System.out.println("Async notification email sent successfully to " + adminEmail);
        } catch (Exception e) {
            System.err.println("Failed to send notification email asynchronously: " + e.getMessage());
        }
    }
}
