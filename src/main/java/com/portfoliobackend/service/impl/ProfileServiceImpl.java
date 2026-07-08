package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.Profile;
import com.portfoliobackend.repository.ProfileRepository;
import com.portfoliobackend.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile getProfile() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) {
            Profile defaultProfile = Profile.builder()
                    .fullName("Nguyễn Bùi Tấn Hiển")
                    .title("Fullstack Developer")
                    .phone("0898198818")
                    .email("hiennguyenbuitan@gmail.com")
                    .location("Ho Chi Minh City, Vietnam")
                    .objective("I am a full-stack developer specializing in Java, proficient in backend frameworks such as Spring Boot, Spring Security and Spring Data JPA, as well as frontend technologies like ReactJS and NextJS. My goal is to find a full-stack developer internship position where I can contribute to building high-quality, performance-optimized products, solving real-world problems, and delivering sustainable value to the business through a sense of responsibility and a rapid learning ability.")
                    .githubUrl("https://github.com/nguyenbuitanhien-dev")
                    .linkedinUrl("https://linkedin.com/in/nguyen-bui-tan-hien")
                    .avatarUrl("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&h=200&fit=crop")
                    .build();
            return profileRepository.save(defaultProfile);
        }
        return profiles.get(0);
    }

    @Override
    public Profile updateProfile(Profile newProfile) {
        Profile existing = getProfile();
        newProfile.setId(existing.getId());
        return profileRepository.save(newProfile);
    }
}
