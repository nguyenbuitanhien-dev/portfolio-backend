package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.Award;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.AwardRepository;
import com.portfoliobackend.service.AwardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;

    public AwardServiceImpl(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @Override
    public List<Award> getAllAwards() {
        return awardRepository.findAll();
    }

    @Override
    public Award getAwardById(String id) {
        return awardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Award not found with id: " + id));
    }

    @Override
    public Award createAward(Award award) {
        return awardRepository.save(award);
    }

    @Override
    public Award updateAward(String id, Award award) {
        if (!awardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Award not found with id: " + id);
        }
        award.setId(id);
        return awardRepository.save(award);
    }

    @Override
    public void deleteAward(String id) {
        if (!awardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Award not found with id: " + id);
        }
        awardRepository.deleteById(id);
    }
}
