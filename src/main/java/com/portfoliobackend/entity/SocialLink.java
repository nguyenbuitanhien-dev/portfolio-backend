package com.portfoliobackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "social_links")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {
    @Id
    private String id;
    private String platform;
    private String url;
    private String icon;
    private Integer sortOrder;
}
