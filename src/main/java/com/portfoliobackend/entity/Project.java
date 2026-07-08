package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    private String id;
    private String name;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<String> highlights;
    private List<String> techStack;
    private String projectUrl;
    private String githubUrl;
    private Integer sortOrder;

    @Builder.Default
    private Integer views = 0;
}
