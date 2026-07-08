package com.portfoliobackend.service;

import com.portfoliobackend.entity.Education;
import java.util.List;

public interface EducationService {
    List<Education> getAllEducation();
    Education getEducationById(String id);
    Education createEducation(Education education);
    Education updateEducation(String id, Education education);
    void deleteEducation(String id);
}
