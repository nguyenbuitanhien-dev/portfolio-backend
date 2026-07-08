package com.portfoliobackend.controller;

import com.portfoliobackend.entity.Skill;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills() {
        List<Skill> list = skillService.getAllSkills();
        return ResponseEntity.ok(ApiResponse.success("Skill list retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Skill>> getSkillById(@PathVariable String id) {
        Skill skill = skillService.getSkillById(id);
        return ResponseEntity.ok(ApiResponse.success("Skill retrieved successfully", skill));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Skill>> createSkill(@RequestBody Skill skill) {
        Skill saved = skillService.createSkill(skill);
        return ResponseEntity.ok(ApiResponse.success("Skill created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Skill>> updateSkill(@PathVariable String id, @RequestBody Skill skill) {
        Skill updated = skillService.updateSkill(id, skill);
        return ResponseEntity.ok(ApiResponse.success("Skill updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@PathVariable String id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok(ApiResponse.success("Skill deleted successfully"));
    }
}
