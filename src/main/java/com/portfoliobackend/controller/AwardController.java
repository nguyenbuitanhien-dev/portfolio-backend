package com.portfoliobackend.controller;

import com.portfoliobackend.entity.Award;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.AwardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    private final AwardService awardService;

    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Award>>> getAllAwards() {
        List<Award> list = awardService.getAllAwards();
        return ResponseEntity.ok(ApiResponse.success("Award list retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Award>> getAwardById(@PathVariable String id) {
        Award award = awardService.getAwardById(id);
        return ResponseEntity.ok(ApiResponse.success("Award retrieved successfully", award));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Award>> createAward(@RequestBody Award award) {
        Award saved = awardService.createAward(award);
        return ResponseEntity.ok(ApiResponse.success("Award created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Award>> updateAward(@PathVariable String id, @RequestBody Award award) {
        Award updated = awardService.updateAward(id, award);
        return ResponseEntity.ok(ApiResponse.success("Award updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAward(@PathVariable String id) {
        awardService.deleteAward(id);
        return ResponseEntity.ok(ApiResponse.success("Award deleted successfully"));
    }
}
