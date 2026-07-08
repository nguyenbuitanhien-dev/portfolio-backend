package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.SocialLink;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.SocialLinkRepository;
import com.portfoliobackend.service.SocialLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialLinkRepository socialLinkRepository;

    public SocialLinkServiceImpl(SocialLinkRepository socialLinkRepository) {
        this.socialLinkRepository = socialLinkRepository;
    }

    @Override
    public List<SocialLink> getAllSocialLinks() {
        return socialLinkRepository.findAll();
    }

    @Override
    public SocialLink getSocialLinkById(String id) {
        return socialLinkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Social link not found with id: " + id));
    }

    @Override
    public SocialLink createSocialLink(SocialLink socialLink) {
        return socialLinkRepository.save(socialLink);
    }

    @Override
    public SocialLink updateSocialLink(String id, SocialLink socialLink) {
        if (!socialLinkRepository.existsById(id)) {
            throw new ResourceNotFoundException("Social link not found with id: " + id);
        }
        socialLink.setId(id);
        return socialLinkRepository.save(socialLink);
    }

    @Override
    public void deleteSocialLink(String id) {
        if (!socialLinkRepository.existsById(id)) {
            throw new ResourceNotFoundException("Social link not found with id: " + id);
        }
        socialLinkRepository.deleteById(id);
    }
}
