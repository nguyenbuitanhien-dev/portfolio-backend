package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDate;

@Document(collection = "educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    @Id
    private String id;
    private String schoolName;
    private String major;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double gpa;
    private Double gpaScale;
}
