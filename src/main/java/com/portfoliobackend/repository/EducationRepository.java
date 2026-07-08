package com.portfoliobackend.repository;

import com.portfoliobackend.entity.Education;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends MongoRepository<Education, String> {
}
