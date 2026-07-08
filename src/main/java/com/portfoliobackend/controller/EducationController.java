package com.portfoliobackend.controller;

import com.portfoliobackend.entity.Education;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Education>>> getAllEducation() {
        List<Education> list = educationService.getAllEducation();
        return ResponseEntity.ok(ApiResponse.success("Education list retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Education>> getEducationById(@PathVariable String id) {
        Education education = educationService.getEducationById(id);
        return ResponseEntity.ok(ApiResponse.success("Education retrieved successfully", education));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Education>> createEducation(@RequestBody Education education) {
        Education saved = educationService.createEducation(education);
        return ResponseEntity.ok(ApiResponse.success("Education created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Education>> updateEducation(@PathVariable String id, @RequestBody Education education) {
        Education updated = educationService.updateEducation(id, education);
        return ResponseEntity.ok(ApiResponse.success("Education updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEducation(@PathVariable String id) {
        educationService.deleteEducation(id);
        return ResponseEntity.ok(ApiResponse.success("Education deleted successfully"));
    }
}
