package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.ContactMessage;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.ContactMessageRepository;
import com.portfoliobackend.service.ContactMessageService;
import com.portfoliobackend.service.EmailService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final EmailService emailService;

    public ContactMessageServiceImpl(ContactMessageRepository contactMessageRepository,
                                     SimpMessagingTemplate messagingTemplate,
                                     EmailService emailService) {
        this.contactMessageRepository = contactMessageRepository;
        this.messagingTemplate = messagingTemplate;
        this.emailService = emailService;
    }

    @Override
    public ContactMessage submitMessage(ContactMessage message) {
        message.setId(null);
        message.setCreatedAt(LocalDateTime.now());
        message.setRead(false);
        ContactMessage saved = contactMessageRepository.save(message);
        
        // Broadcast new message in real-time
        messagingTemplate.convertAndSend("/topic/contact-messages", saved);
        
        // Send email notification asynchronously
        emailService.sendContactNotification(
                saved.getName(),
                saved.getEmail(),
                saved.getSubject(),
                saved.getMessage()
        );
        
        return saved;
    }

    @Override
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }

    @Override
    public ContactMessage getMessageById(String id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    @Override
    public ContactMessage markAsRead(String id) {
        ContactMessage message = getMessageById(id);
        message.setRead(true);
        return contactMessageRepository.save(message);
    }

    @Override
    public void deleteMessage(String id) {
        if (!contactMessageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Message not found with id: " + id);
        }
        contactMessageRepository.deleteById(id);
    }
}
