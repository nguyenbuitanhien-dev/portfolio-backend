package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.Project;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.ProjectRepository;
import com.portfoliobackend.service.ProjectService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ProjectServiceImpl(ProjectRepository projectRepository, SimpMessagingTemplate messagingTemplate) {
        this.projectRepository = projectRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        
        project.setViews(project.getViews() == null ? 1 : project.getViews() + 1);
        Project saved = projectRepository.save(project);
        
        // Broadcast project view increment in real-time
        messagingTemplate.convertAndSend("/topic/project-views", saved);
        
        return saved;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(String id, Project project) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        project.setId(id);
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(String id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }
}
