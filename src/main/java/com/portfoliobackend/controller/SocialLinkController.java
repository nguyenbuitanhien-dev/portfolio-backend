package com.portfoliobackend.controller;

import com.portfoliobackend.entity.SocialLink;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.SocialLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-links")
public class SocialLinkController {

    private final SocialLinkService socialLinkService;

    public SocialLinkController(SocialLinkService socialLinkService) {
        this.socialLinkService = socialLinkService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SocialLink>>> getAllSocialLinks() {
        List<SocialLink> list = socialLinkService.getAllSocialLinks();
        return ResponseEntity.ok(ApiResponse.success("Social links retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialLink>> getSocialLinkById(@PathVariable String id) {
        SocialLink socialLink = socialLinkService.getSocialLinkById(id);
        return ResponseEntity.ok(ApiResponse.success("Social link retrieved successfully", socialLink));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SocialLink>> createSocialLink(@RequestBody SocialLink socialLink) {
        SocialLink saved = socialLinkService.createSocialLink(socialLink);
        return ResponseEntity.ok(ApiResponse.success("Social link created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialLink>> updateSocialLink(@PathVariable String id, @RequestBody SocialLink socialLink) {
        SocialLink updated = socialLinkService.updateSocialLink(id, socialLink);
        return ResponseEntity.ok(ApiResponse.success("Social link updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSocialLink(@PathVariable String id) {
        socialLinkService.deleteSocialLink(id);
        return ResponseEntity.ok(ApiResponse.success("Social link deleted successfully"));
    }
}
