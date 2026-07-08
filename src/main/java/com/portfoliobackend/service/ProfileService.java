package com.portfoliobackend.service;

import com.portfoliobackend.entity.Profile;

public interface ProfileService {
    Profile getProfile();
    Profile updateProfile(Profile newProfile);
}
