package com.portfoliobackend.service;

import com.portfoliobackend.entity.Award;
import java.util.List;

public interface AwardService {
    List<Award> getAllAwards();
    Award getAwardById(String id);
    Award createAward(Award award);
    Award updateAward(String id, Award award);
    void deleteAward(String id);
}
