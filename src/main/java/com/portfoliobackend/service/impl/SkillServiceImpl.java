package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.Skill;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.SkillRepository;
import com.portfoliobackend.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(String id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(String id, Skill skill) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skill.setId(id);
        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(String id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
}
