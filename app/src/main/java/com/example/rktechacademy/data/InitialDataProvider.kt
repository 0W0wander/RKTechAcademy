package com.example.rktechacademy.data

import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.LessonDifficulty
import com.example.rktechacademy.data.model.LessonType

object InitialDataProvider {
    
    fun getInitialModules(): List<LearningModule> {
        return listOf(
            LearningModule(
                id = "new_product_dev",
                title = "New Product Development",
                description = "Learn the fundamentals of bringing innovative ideas from concept to market",
                iconResource = "ic_innovation",
                color = "#1E3A8A",
                orderIndex = 1
            ),
            LearningModule(
                id = "technical_solutions",
                title = "Technical Solutions",
                description = "Master modern technology implementation and development practices",
                iconResource = "ic_technical",
                color = "#2196F3",
                orderIndex = 2
            ),
            LearningModule(
                id = "hr_it_solutions",
                title = "HR & IT Solutions",
                description = "Understand business operations, HR processes, and IT management",
                iconResource = "ic_business",
                color = "#FF9800",
                orderIndex = 3
            ),
            LearningModule(
                id = "live_talent",
                title = "Live Talent",
                description = "Explore creative industries, performance arts, and talent management",
                iconResource = "ic_creative",
                color = "#9C27B0",
                orderIndex = 4
            ),
            LearningModule(
                id = "rkb_labs",
                title = "RKB Labs",
                description = "Hands-on projects and real-world applications of your learning",
                iconResource = "ic_labs",
                color = "#F44336",
                orderIndex = 5
            )
        )
    }
    
    fun getInitialLessons(): List<Lesson> {
        return listOf(
            // New Product Development Lessons
            Lesson(
                id = "npd_001",
                moduleId = "new_product_dev",
                title = "Introduction to Innovation and Product Development",
                description = "Learn the fundamentals of bringing ideas from concept to market with RK Blueprints methodology",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_001"] ?: ""),
                duration = 600, // 10 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("innovation", "product-development", "strategy", "concept-to-market"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_001_text",
                moduleId = "new_product_dev",
                title = "Key Innovation Concepts",
                description = "Quick review of the main innovation principles discussed in the video",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    ## Key Innovation Concepts
                    
                    **Product Development Lifecycle:**
                    • Ideation - Generate and evaluate ideas
                    • Research - Validate market need and feasibility
                    • Design - Create prototypes and specifications
                    • Development - Build and test the product
                    • Launch - Bring product to market
                    
                    **Innovation Framework:**
                    RK Blueprints uses a human-centered approach that focuses on:
                    - Understanding customer pain points
                    - Creating solutions that matter
                    - Iterating based on feedback
                    - Scaling successful innovations
                    
                    **Remember:** Innovation isn't just about new technology - it's about creating value for users in meaningful ways.
                """.trimIndent(),
                duration = 180, // 3 minutes reading time
                orderIndex = 2,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("concepts", "review", "innovation"),
                lessonType = LessonType.CONCEPT_REVIEW
            ),
            Lesson(
                id = "npd_002",
                moduleId = "new_product_dev",
                title = "Market Research and Customer Validation",
                description = "Discover how to identify market opportunities and validate your product ideas effectively",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_002"] ?: ""),
                duration = 720, // 12 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("market-research", "validation", "customer-needs", "opportunity"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_002_text",
                moduleId = "new_product_dev",
                title = "Market Research Checklist",
                description = "Essential steps for conducting effective market research",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    ## Market Research Checklist
                    
                    **Before You Start:**
                    ✅ Define your target customer clearly
                    ✅ Identify the problem you're solving
                    ✅ Set specific research goals
                    
                    **Research Methods:**
                    • **Surveys** - Quantitative data from potential customers
                    • **Interviews** - Deep qualitative insights
                    • **Competitor Analysis** - Understand the landscape
                    • **Market Sizing** - Calculate potential opportunity
                    
                    **Key Questions to Answer:**
                    1. Who exactly is your customer?
                    2. How big is the market opportunity?
                    3. What alternatives exist today?
                    4. What would customers pay for your solution?
                    5. How will you reach your customers?
                    
                    **Pro Tip:** Always validate with real customers, not just friends and family!
                """.trimIndent(),
                duration = 240, // 4 minutes reading time
                orderIndex = 4,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("checklist", "research", "validation"),
                lessonType = LessonType.SUMMARY
            ),
            Lesson(
                id = "npd_003",
                moduleId = "new_product_dev",
                title = "Design Thinking and Prototyping",
                description = "Master the art of design thinking and create powerful prototypes that bring ideas to life",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_003"] ?: ""),
                duration = 900, // 15 minutes
                orderIndex = 5,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("design-thinking", "prototyping", "user-experience", "iteration"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_004",
                moduleId = "new_product_dev",
                title = "MVP Development and Launch Strategy",
                description = "Build minimum viable products and create successful go-to-market strategies",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_004"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_004"] ?: ""),
                duration = 1020, // 17 minutes
                orderIndex = 6,
                difficulty = LessonDifficulty.ADVANCED,
                tags = listOf("mvp", "launch-strategy", "go-to-market", "product-management"),
                lessonType = LessonType.VIDEO
            ),
            
            // Technical Solutions Lessons  
            Lesson(
                id = "tech_001",
                moduleId = "technical_solutions",
                title = "Modern Software Architecture Principles",
                description = "Understand scalable system design and architecture patterns for enterprise solutions",
                videoUrl = VideoUrlUpdater.videoUrlMappings["tech_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["tech_001"] ?: ""),
                duration = 1080, // 18 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("software-architecture", "scalability", "design-patterns", "enterprise"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "tech_002",
                moduleId = "technical_solutions",
                title = "Cloud Computing and DevOps Fundamentals",
                description = "Master cloud platforms, CI/CD pipelines, and modern deployment strategies",
                videoUrl = VideoUrlUpdater.videoUrlMappings["tech_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["tech_002"] ?: ""),
                duration = 960, // 16 minutes
                orderIndex = 2,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("cloud-computing", "devops", "ci-cd", "aws", "azure"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "tech_003",
                moduleId = "technical_solutions",
                title = "Full-Stack Development Best Practices",
                description = "Learn modern web development techniques and full-stack application architecture",
                videoUrl = VideoUrlUpdater.videoUrlMappings["tech_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["tech_003"] ?: ""),
                duration = 1200, // 20 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.ADVANCED,
                tags = listOf("full-stack", "web-development", "api-design", "database"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "tech_004",
                moduleId = "technical_solutions",
                title = "AI and Machine Learning Integration",
                description = "Integrate AI and ML capabilities into your technical solutions effectively",
                videoUrl = VideoUrlUpdater.videoUrlMappings["tech_004"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["tech_004"] ?: ""),
                duration = 1440, // 24 minutes
                orderIndex = 4,
                difficulty = LessonDifficulty.EXPERT,
                tags = listOf("artificial-intelligence", "machine-learning", "integration", "automation"),
                lessonType = LessonType.VIDEO
            ),
            
            // HR & IT Solutions Lessons
            Lesson(
                id = "hr_001",
                moduleId = "hr_it_solutions",
                title = "Modern HR Technology Stack",
                description = "Explore the latest HR technologies and how they transform people management",
                videoUrl = VideoUrlUpdater.videoUrlMappings["hr_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["hr_001"] ?: ""),
                duration = 600, // 10 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("hr-technology", "people-management", "automation", "workflow"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "hr_002",
                moduleId = "hr_it_solutions",
                title = "IT Infrastructure and Security Management",
                description = "Best practices for managing IT infrastructure, security, and compliance",
                videoUrl = VideoUrlUpdater.videoUrlMappings["hr_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["hr_002"] ?: ""),
                duration = 840, // 14 minutes
                orderIndex = 2,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("it-infrastructure", "security", "compliance", "management"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "hr_003",
                moduleId = "hr_it_solutions",
                title = "Digital Transformation in Organizations",
                description = "Guide your organization through successful digital transformation initiatives",
                videoUrl = VideoUrlUpdater.videoUrlMappings["hr_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["hr_003"] ?: ""),
                duration = 960, // 16 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.ADVANCED,
                tags = listOf("digital-transformation", "change-management", "strategy", "culture"),
                lessonType = LessonType.VIDEO
            ),
            
            // Live Talent Lessons
            Lesson(
                id = "talent_001",
                moduleId = "live_talent",
                title = "Creative Industry Landscape and Opportunities",
                description = "Navigate the creative industries and discover talent management opportunities",
                videoUrl = VideoUrlUpdater.videoUrlMappings["talent_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["talent_001"] ?: ""),
                duration = 540, // 9 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("creative-industry", "talent-management", "opportunities", "networking"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "talent_002",
                moduleId = "live_talent",
                title = "Event Production and Management",
                description = "Master the art of planning, organizing, and executing successful live events",
                videoUrl = VideoUrlUpdater.videoUrlMappings["talent_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["talent_002"] ?: ""),
                duration = 780, // 13 minutes
                orderIndex = 2,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("event-production", "project-management", "logistics", "coordination"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "talent_003",
                moduleId = "live_talent",
                title = "Music Business and Artist Development",
                description = "Learn about artist development, music business, and creative collaboration",
                videoUrl = VideoUrlUpdater.videoUrlMappings["talent_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["talent_003"] ?: ""),
                duration = 900, // 15 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("music-business", "artist-development", "collaboration", "industry"),
                lessonType = LessonType.VIDEO
            ),
            
            // RKB Labs Lessons - Hands-on Projects
            Lesson(
                id = "labs_001",
                moduleId = "rkb_labs",
                title = "Lab Project: Build Your First Mobile App",
                description = "Step-by-step hands-on project to create and deploy your first mobile application",
                videoUrl = VideoUrlUpdater.videoUrlMappings["labs_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["labs_001"] ?: ""),
                duration = 1800, // 30 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("hands-on", "mobile-app", "development", "project", "tutorial"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "labs_002",
                moduleId = "rkb_labs",
                title = "Lab Project: E-commerce Platform Development",
                description = "Build a complete e-commerce solution from design to deployment",
                videoUrl = VideoUrlUpdater.videoUrlMappings["labs_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["labs_002"] ?: ""),
                duration = 2400, // 40 minutes
                orderIndex = 2,
                difficulty = LessonDifficulty.ADVANCED,
                tags = listOf("e-commerce", "full-stack", "project", "real-world", "portfolio"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "labs_003",
                moduleId = "rkb_labs",
                title = "Lab Project: AI-Powered Business Solution",
                description = "Create an AI-powered application that solves real business problems",
                videoUrl = VideoUrlUpdater.videoUrlMappings["labs_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["labs_003"] ?: ""),
                duration = 2700, // 45 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.EXPERT,
                tags = listOf("artificial-intelligence", "business-solution", "advanced-project", "innovation"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "labs_004",
                moduleId = "rkb_labs",
                title = "Capstone Project: Launch Your Startup",
                description = "Apply everything you've learned to launch your own tech startup from scratch",
                videoUrl = VideoUrlUpdater.videoUrlMappings["labs_004"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["labs_004"] ?: ""),
                duration = 3600, // 60 minutes
                orderIndex = 4,
                difficulty = LessonDifficulty.EXPERT,
                tags = listOf("capstone", "startup", "entrepreneurship", "launch", "business"),
                lessonType = LessonType.VIDEO
            )
        )
    }
}