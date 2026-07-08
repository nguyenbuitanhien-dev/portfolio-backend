package com.portfoliobackend.service;

import com.portfoliobackend.entity.Skill;
import java.util.List;

public interface SkillService {
    List<Skill> getAllSkills();
    Skill getSkillById(String id);
    Skill createSkill(Skill skill);
    Skill updateSkill(String id, Skill skill);
    void deleteSkill(String id);
}
