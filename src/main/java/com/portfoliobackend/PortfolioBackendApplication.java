package com.portfoliobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.portfoliobackend.repository")
@EnableAsync
public class PortfolioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioBackendApplication.class, args);
    }

}
