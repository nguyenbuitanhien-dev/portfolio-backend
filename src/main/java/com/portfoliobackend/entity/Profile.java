package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    private String id;
    private String fullName;
    private String title;
    private String phone;
    private String email;
    private String githubUrl;
    private String linkedinUrl;
    private String location;
    private String objective;
    private String avatarUrl;
}
