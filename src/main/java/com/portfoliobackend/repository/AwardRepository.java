package com.portfoliobackend.repository;

import com.portfoliobackend.entity.Award;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends MongoRepository<Award, String> {
}
