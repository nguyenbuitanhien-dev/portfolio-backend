package com.portfoliobackend.config;

import com.portfoliobackend.entity.*;
import com.portfoliobackend.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final AwardRepository awardRepository;
    private final SocialLinkRepository socialLinkRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    public DataSeeder(UserRepository userRepository,
                      ProfileRepository profileRepository,
                      EducationRepository educationRepository,
                      SkillRepository skillRepository,
                      ProjectRepository projectRepository,
                      AwardRepository awardRepository,
                      SocialLinkRepository socialLinkRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.awardRepository = awardRepository;
        this.socialLinkRepository = socialLinkRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAdminUser();
        seedProfile();
        seedEducation();
        seedSkills();
        seedProjects();
        seedAwards();
        seedSocialLinks();
    }

    private void seedAdminUser() {
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = User.builder()
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .role("ADMIN")
                    .build();
            userRepository.save(admin);
        }
    }

    private void seedProfile() {
        if (profileRepository.findAll().isEmpty()) {
            Profile profile = Profile.builder()
                    .fullName("Nguyễn Bùi Tấn Hiển")
                    .title("Fullstack Developer")
                    .phone("0898198818")
                    .email("hiennguyenbuitan@gmail.com")
                    .location("Ho Chi Minh City, Vietnam")
                    .objective("I am a full-stack developer specializing in Java, proficient in backend frameworks such as Spring Boot, Spring Security and Spring Data JPA, as well as frontend technologies like ReactJS and NextJS. My goal is to find a full-stack developer internship position where I can contribute to building high-quality, performance-optimized products, solving real-world problems, and delivering sustainable value to the business through a sense of responsibility and a rapid learning ability.")
                    .githubUrl("https://github.com/nguyenbuitanhien-dev")
                    .linkedinUrl("https://linkedin.com/in/nguyen-bui-tan-hien")
                    .avatarUrl("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&h=200&fit=crop")
                    .build();
            profileRepository.save(profile);
        }
    }

    private void seedEducation() {
        if (educationRepository.findAll().isEmpty()) {
            Education education = Education.builder()
                    .schoolName("Industrial University of Ho Chi Minh City (IUH)")
                    .major("Software Engineering")
                    .startDate(LocalDate.of(2022, 8, 1))
                    .endDate(LocalDate.of(2027, 3, 1))
                    .gpa(3.47)
                    .gpaScale(4.0)
                    .build();
            educationRepository.save(education);
        }
    }

    private void seedSkills() {
        if (skillRepository.findAll().isEmpty()) {
            List<Skill> skills = List.of(
                    // Languages
                    new Skill(null, "Java", SkillCategory.LANGUAGE, 1),
                    new Skill(null, "JavaScript", SkillCategory.LANGUAGE, 2),
                    new Skill(null, "TypeScript", SkillCategory.LANGUAGE, 3),
                    
                    // Frontend
                    new Skill(null, "ReactJS", SkillCategory.FRONTEND, 4),
                    new Skill(null, "NextJS", SkillCategory.FRONTEND, 5),
                    new Skill(null, "Tailwind CSS", SkillCategory.FRONTEND, 6),
                    
                    // Backend
                    new Skill(null, "Spring Boot", SkillCategory.BACKEND, 7),
                    new Skill(null, "Spring Security", SkillCategory.BACKEND, 8),
                    new Skill(null, "Spring Data JPA", SkillCategory.BACKEND, 9),
                    new Skill(null, "NodeJS", SkillCategory.BACKEND, 10),
                    
                    // Database & Tools
                    new Skill(null, "MariaDB", SkillCategory.DATABASE_TOOLS, 11),
                    new Skill(null, "MongoDB", SkillCategory.DATABASE_TOOLS, 12),
                    new Skill(null, "Redis", SkillCategory.DATABASE_TOOLS, 13),
                    new Skill(null, "MySQL", SkillCategory.DATABASE_TOOLS, 14),
                    
                    // Others
                    new Skill(null, "Docker", SkillCategory.OTHER, 15),
                    new Skill(null, "CI/CD (GitHub Actions)", SkillCategory.OTHER, 16),
                    new Skill(null, "Git", SkillCategory.OTHER, 17)
            );
            skillRepository.saveAll(skills);
        }
    }

    private void seedProjects() {
        if (projectRepository.findAll().isEmpty()) {
            Project project1 = Project.builder()
                    .name("Zala – Multi-platform Messaging System")
                    .role("Fullstack Developer")
                    .startDate(LocalDate.of(2025, 11, 1))
                    .endDate(LocalDate.of(2026, 6, 30))
                    .description("Built a cross-platform messaging application using React Native, Expo, Node.js and MongoDB. Implemented real-time chat with Socket.IO and low-latency livestream using LiveKit/WebRTC. Developed authentication, messaging, and livestream features with responsive mobile UI. Deployed the application using Vercel and DigitalOcean.")
                    .highlights(List.of(
                            "Built a cross-platform messaging application using React Native, Expo, Node.js and MongoDB.",
                            "Implemented real-time chat with Socket.IO and low-latency livestream using LiveKit/WebRTC.",
                            "Developed authentication, messaging, and livestream features with responsive mobile UI.",
                            "Deployed the application using Vercel and DigitalOcean."
                    ))
                    .techStack(List.of("React Native", "Expo", "Node.js", "TypeScript", "MongoDB", "Socket.IO", "LiveKit", "JWT"))
                    .projectUrl("https://host-frontend-mu.vercel.app")
                    .githubUrl("https://github.com/nguyenbuitanhien-dev/zala-messaging")
                    .sortOrder(1)
                    .build();

            Project project2 = Project.builder()
                    .name("Event Management System")
                    .role("Fullstack Developer")
                    .startDate(LocalDate.of(2025, 11, 1))
                    .endDate(LocalDate.of(2026, 6, 30))
                    .description("Developed 15+ RESTful APIs with Spring Boot for event, authentication, scheduling, and analytics. Built JWT authentication, RBAC, QR attendance, and statistical dashboards. Designed Kafka notification service, reducing notification latency by ~60%. Integrated Gemini AI and deployed with Docker, AWS, and GitHub Actions.")
                    .highlights(List.of(
                            "Developed 15+ RESTful APIs with Spring Boot for event, authentication, scheduling, and analytics.",
                            "Built JWT authentication, RBAC, QR attendance, and statistical dashboards.",
                            "Designed Kafka notification service, reducing notification latency by ~60%.",
                            "Integrated Gemini AI and deployed with Docker, AWS, and GitHub Actions."
                    ))
                    .techStack(List.of("Java", "Spring Boot", "React", "MariaDB", "MongoDB", "Redis", "Kafka", "Docker", "AWS"))
                    .projectUrl("https://fitiuh-events.io.vn")
                    .githubUrl("https://github.com/nguyenbuitanhien-dev/Event-Management-Portal")
                    .sortOrder(2)
                    .build();

            projectRepository.saveAll(List.of(project1, project2));
        }
    }

    private void seedAwards() {
        if (awardRepository.findAll().isEmpty()) {
            Award award = Award.builder()
                    .title("SSRC-I-2026 | Scientific Research Participant")
                    .organization("Industrial University of Ho Chi Minh City")
                    .date(LocalDate.of(2026, 5, 1))
                    .description("Researched and proposed an AI-driven automated event scheduling system using Gemini 3.1 Flash Lite.")
                    .build();
            awardRepository.save(award);
        }
    }

    private void seedSocialLinks() {
        if (socialLinkRepository.findAll().isEmpty()) {
            List<SocialLink> socialLinks = List.of(
                    new SocialLink(null, "GitHub", "https://github.com/nguyenbuitanhien-dev", "github", 1),
                    new SocialLink(null, "LinkedIn", "https://linkedin.com/in/nguyen-bui-tan-hien", "linkedin", 2)
            );
            socialLinkRepository.saveAll(socialLinks);
        }
    }
}
