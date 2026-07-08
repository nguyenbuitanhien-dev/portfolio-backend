package com.portfoliobackend.service.impl;

import com.portfoliobackend.entity.Education;
import com.portfoliobackend.exception.ResourceNotFoundException;
import com.portfoliobackend.repository.EducationRepository;
import com.portfoliobackend.service.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    @Override
    public Education getEducationById(String id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));
    }

    @Override
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public Education updateEducation(String id, Education education) {
        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Education not found with id: " + id);
        }
        education.setId(id);
        return educationRepository.save(education);
    }

    @Override
    public void deleteEducation(String id) {
        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Education not found with id: " + id);
        }
        educationRepository.deleteById(id);
    }
}
