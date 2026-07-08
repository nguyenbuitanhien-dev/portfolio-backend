package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    @Id
    private String id;
    private String name;
    private SkillCategory category;
    private Integer sortOrder;
}
