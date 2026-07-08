package com.portfoliobackend.controller;

import com.portfoliobackend.entity.Profile;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Profile>> getProfile() {
        Profile profile = profileService.getProfile();
        return ResponseEntity.ok(ApiResponse.success("Profile retrieved successfully", profile));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Profile>> updateProfile(@RequestBody Profile profile) {
        Profile updated = profileService.updateProfile(profile);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", updated));
    }
}
