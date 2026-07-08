package com.portfoliobackend.service;

import com.portfoliobackend.entity.SocialLink;
import java.util.List;

public interface SocialLinkService {
    List<SocialLink> getAllSocialLinks();
    SocialLink getSocialLinkById(String id);
    SocialLink createSocialLink(SocialLink socialLink);
    SocialLink updateSocialLink(String id, SocialLink socialLink);
    void deleteSocialLink(String id);
}
