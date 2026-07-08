package com.portfoliobackend.service;

import com.portfoliobackend.entity.ContactMessage;
import java.util.List;

public interface ContactMessageService {
    ContactMessage submitMessage(ContactMessage message);
    List<ContactMessage> getAllMessages();
    ContactMessage getMessageById(String id);
    ContactMessage markAsRead(String id);
    void deleteMessage(String id);
}
