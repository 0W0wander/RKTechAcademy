package com.example.rktechacademy.data

import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.LessonDifficulty
import com.example.rktechacademy.data.model.LessonType

/**
 * Updated data provider with fixed lesson structure including lessonType for all lessons
 * This replaces the problematic sections in InitialDataProvider
 */
object InitialDataProviderFix {
    
    fun getUpdatedLessons(): List<Lesson> {
        return listOf(
            // New Product Development Lessons (already updated above)
            
            // Technical Solutions Lessons
            Lesson(
                id = "tech_001",
                moduleId = "technical_solutions",
                title = "Modern Software Architecture Principles",
                description = "Understand scalable system design and architecture patterns for enterprise solutions",
                videoUrl = "REPLACE_WITH_RAHEEM_VIDEO_URL_5",
                thumbnailUrl = "https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg",
                duration = 1080, // 18 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("software-architecture", "scalability", "design-patterns", "enterprise"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "tech_001_text",
                moduleId = "technical_solutions",
                title = "Architecture Patterns Overview",
                description = "Key software architecture patterns every developer should know",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    ## Essential Architecture Patterns
                    
                    **Monolithic Architecture:**
                    • Single deployable unit
                    • Simple to develop and test initially
                    • Can become complex as system grows
                    
                    **Microservices Architecture:**
                    • Independent, loosely coupled services
                    • Scalable and maintainable
                    • Requires DevOps maturity
                    
                    **Layered Architecture:**
                    • Presentation → Business → Data layers
                    • Clear separation of concerns
                    • Easy to understand and maintain
                    
                    **Event-Driven Architecture:**
                    • Components communicate via events
                    • Highly scalable and reactive
                    • Good for real-time systems
                    
                    **Key Principles:**
                    - Single Responsibility
                    - Loose Coupling
                    - High Cohesion
                    - Scalability by Design
                """.trimIndent(),
                duration = 300, // 5 minutes reading time
                orderIndex = 2,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("patterns", "architecture", "overview"),
                lessonType = LessonType.CONCEPT_REVIEW
            ),
            Lesson(
                id = "tech_002",
                moduleId = "technical_solutions",
                title = "Cloud Computing and DevOps Fundamentals",
                description = "Master cloud platforms, CI/CD pipelines, and modern deployment strategies",
                videoUrl = "REPLACE_WITH_RAHEEM_VIDEO_URL_6",
                thumbnailUrl = "https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg",
                duration = 960, // 16 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("cloud-computing", "devops", "ci-cd", "aws", "azure"),
                lessonType = LessonType.VIDEO
            ),
            
            // HR & IT Solutions Lessons
            Lesson(
                id = "hr_001",
                moduleId = "hr_it_solutions",
                title = "Modern HR Technology Stack",
                description = "Explore the latest HR technologies and how they transform people management",
                videoUrl = "REPLACE_WITH_RAHEEM_VIDEO_URL_9",
                thumbnailUrl = "https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg",
                duration = 600, // 10 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("hr-technology", "people-management", "automation", "workflow"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "hr_001_text",
                moduleId = "hr_it_solutions",
                title = "HR Tech Tools Comparison",
                description = "Compare popular HR technology platforms and their features",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    ## Popular HR Technology Platforms
                    
                    **Applicant Tracking Systems (ATS):**
                    • Workday - Enterprise-grade, comprehensive
                    • BambooHR - SMB-focused, user-friendly
                    • Greenhouse - Recruiting-focused
                    
                    **HRIS Platforms:**
                    • Workday - Full suite HR platform
                    • SuccessFactors - SAP-integrated solution
                    • ADP - Payroll and HR combined
                    
                    **Performance Management:**
                    • 15Five - Continuous feedback
                    • Lattice - OKRs and reviews
                    • Culture Amp - Employee engagement
                    
                    **Key Features to Consider:**
                    ✅ Integration capabilities
                    ✅ User experience
                    ✅ Reporting and analytics
                    ✅ Compliance features
                    ✅ Scalability
                    ✅ Mobile accessibility
                    
                    **Implementation Tips:**
                    1. Start with core needs assessment
                    2. Plan for change management
                    3. Ensure data migration strategy
                    4. Train users thoroughly
                """.trimIndent(),
                duration = 420, // 7 minutes reading time
                orderIndex = 2,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("hr-tools", "comparison", "technology"),
                lessonType = LessonType.SUMMARY
            ),
            
            // Live Talent Lessons
            Lesson(
                id = "talent_001",
                moduleId = "live_talent",
                title = "Creative Industry Landscape and Opportunities",
                description = "Navigate the creative industries and discover talent management opportunities",
                videoUrl = "REPLACE_WITH_RAHEEM_VIDEO_URL_12",
                thumbnailUrl = "https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg",
                duration = 540, // 9 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("creative-industry", "talent-management", "opportunities", "networking"),
                lessonType = LessonType.VIDEO
            ),
            
            // RKB Labs Lessons - Hands-on Projects
            Lesson(
                id = "labs_001",
                moduleId = "rkb_labs",
                title = "Lab Project: Build Your First Mobile App",
                description = "Step-by-step hands-on project to create and deploy your first mobile application",
                videoUrl = "REPLACE_WITH_RAHEEM_VIDEO_URL_15",
                thumbnailUrl = "https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg",
                duration = 1800, // 30 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("hands-on", "mobile-app", "development", "project", "tutorial"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "labs_001_text",
                moduleId = "rkb_labs",
                title = "Mobile App Development Checklist",
                description = "Essential steps for your first mobile app project",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    ## Mobile App Development Checklist
                    
                    **Planning Phase:**
                    ✅ Define app purpose and target audience
                    ✅ Choose platform (iOS, Android, or both)
                    ✅ Create wireframes and user flows
                    ✅ Plan data storage and backend needs
                    
                    **Development Phase:**
                    ✅ Set up development environment
                    ✅ Create project structure
                    ✅ Implement core features first
                    ✅ Add UI/UX polish
                    ✅ Test on real devices
                    
                    **Pre-Launch:**
                    ✅ Beta testing with real users
                    ✅ Performance optimization
                    ✅ App store compliance check
                    ✅ Prepare marketing materials
                    
                    **Launch & Post-Launch:**
                    ✅ Submit to app stores
                    ✅ Monitor crash reports
                    ✅ Gather user feedback
                    ✅ Plan updates and iterations
                    
                    **Pro Tips:**
                    • Start simple - you can always add features later
                    • Focus on one core problem your app solves
                    • Test early and often with real users
                    • Plan for app store review process (can take 1-7 days)
                """.trimIndent(),
                duration = 360, // 6 minutes reading time
                orderIndex = 2,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("checklist", "mobile", "development"),
                lessonType = LessonType.SUMMARY
            )
        )
    }
}