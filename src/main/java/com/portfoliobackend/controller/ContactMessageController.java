package com.portfoliobackend.controller;

import com.portfoliobackend.entity.ContactMessage;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.ContactMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContactMessage>> submitMessage(@RequestBody ContactMessage message) {
        ContactMessage saved = contactMessageService.submitMessage(message);
        return ResponseEntity.ok(ApiResponse.success("Message sent successfully", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContactMessage>>> getAllMessages() {
        List<ContactMessage> messages = contactMessageService.getAllMessages();
        return ResponseEntity.ok(ApiResponse.success("Messages retrieved successfully", messages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactMessage>> getMessageById(@PathVariable String id) {
        ContactMessage message = contactMessageService.getMessageById(id);
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully", message));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<ContactMessage>> markAsRead(@PathVariable String id) {
        ContactMessage updated = contactMessageService.markAsRead(id);
        return ResponseEntity.ok(ApiResponse.success("Message marked as read", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMessage(@PathVariable String id) {
        contactMessageService.deleteMessage(id);
        return ResponseEntity.ok(ApiResponse.success("Message deleted successfully"));
    }
}
