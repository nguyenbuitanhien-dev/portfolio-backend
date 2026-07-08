package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDate;

@Document(collection = "awards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Award {
    @Id
    private String id;
    private String title;
    private String organization;
    private String description;
    private LocalDate date;
}
