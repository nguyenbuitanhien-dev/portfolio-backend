package com.portfoliobackend.controller;

import com.portfoliobackend.entity.Project;
import com.portfoliobackend.payload.ApiResponse;
import com.portfoliobackend.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        List<Project> list = projectService.getAllProjects();
        return ResponseEntity.ok(ApiResponse.success("Project list retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> getProjectById(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(ApiResponse.success("Project retrieved successfully", project));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(@RequestBody Project project) {
        Project saved = projectService.createProject(project);
        return ResponseEntity.ok(ApiResponse.success("Project created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> updateProject(@PathVariable String id, @RequestBody Project project) {
        Project updated = projectService.updateProject(id, project);
        return ResponseEntity.ok(ApiResponse.success("Project updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(ApiResponse.success("Project deleted successfully"));
    }
}
