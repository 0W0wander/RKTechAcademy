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
                description = "Discover the fundamentals of innovation and learn how successful products are born from ideas. Explore the complete product development lifecycle using proven RK Blueprints methodology.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_001"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_001"] ?: ""),
                duration = 900, // 15 minutes
                orderIndex = 1,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("innovation", "product-development", "strategy", "concept-to-market", "rkblueprints"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_001_text",
                moduleId = "new_product_dev",
                title = "Key Innovation Concepts - Study Guide",
                description = "Comprehensive study guide covering the fundamental principles of innovation and product development methodology",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    <h2>🚀 Key Innovation Concepts</h2>
                    
                    <h3>The Complete Product Development Lifecycle</h3>
                    <p><strong>RK Blueprints follows a proven 5-stage methodology:</strong></p>
                    
                    <ol>
                        <li><strong>💡 Ideation</strong> - Generate creative ideas and evaluate their potential</li>
                        <li><strong>🔍 Research</strong> - Validate market need and technical feasibility</li>
                        <li><strong>🎨 Design</strong> - Create prototypes and detailed specifications</li>
                        <li><strong>⚙️ Development</strong> - Build, test, and refine the product</li>
                        <li><strong>🚀 Launch</strong> - Bring the product to market and scale</li>
                    </ol>
                    
                    <h3>RK Blueprints Innovation Framework</h3>
                    <p>Our human-centered approach focuses on:</p>
                    <ul>
                        <li>🎯 <strong>Customer Pain Points:</strong> Understanding real problems people face</li>
                        <li>💡 <strong>Value Creation:</strong> Building solutions that truly matter</li>
                        <li>🔄 <strong>Iterative Improvement:</strong> Continuous feedback and refinement</li>
                        <li>📈 <strong>Scalable Innovation:</strong> Growing successful solutions effectively</li>
                    </ul>
                    
                    <h3>Key Principles to Remember</h3>
                    <blockquote>
                        <p><em>"Innovation isn't just about new technology - it's about creating meaningful value for users and solving real-world problems."</em></p>
                    </blockquote>
                    
                    <p><strong>Success Metrics:</strong></p>
                    <ul>
                        <li>Problem-solution fit</li>
                        <li>User adoption rates</li>
                        <li>Market validation</li>
                        <li>Sustainable growth</li>
                    </ul>
                """.trimIndent(),
                duration = 300, // 5 minutes reading time
                orderIndex = 2,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("concepts", "study-guide", "innovation", "framework"),
                lessonType = LessonType.CONCEPT_REVIEW
            ),
            Lesson(
                id = "npd_002",
                moduleId = "new_product_dev",
                title = "Market Research and Customer Validation",
                description = "Master the art of identifying market opportunities and validating product ideas. Learn proven research techniques, customer interview methods, and data analysis strategies used by successful innovators.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_002"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_002"] ?: ""),
                duration = 1080, // 18 minutes
                orderIndex = 3,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("market-research", "validation", "customer-interviews", "data-analysis", "opportunity-assessment"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_002_text",
                moduleId = "new_product_dev",
                title = "Complete Market Research Toolkit",
                description = "Your comprehensive guide to conducting professional market research and customer validation",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    <h2>📊 Complete Market Research Toolkit</h2>
                    
                    <h3>🎯 Phase 1: Pre-Research Setup</h3>
                    <p><strong>Essential Preparation Steps:</strong></p>
                    <ul>
                        <li>✅ Define your target customer persona clearly</li>
                        <li>✅ Identify the specific problem you're solving</li>
                        <li>✅ Set measurable research objectives</li>
                        <li>✅ Create a research timeline and budget</li>
                        <li>✅ Prepare interview scripts and survey questions</li>
                    </ul>
                    
                    <h3>🔍 Phase 2: Research Methods</h3>
                    
                    <h4>Quantitative Research:</h4>
                    <ul>
                        <li><strong>📋 Online Surveys</strong> - Reach large audiences quickly</li>
                        <li><strong>📈 Analytics Data</strong> - Existing user behavior patterns</li>
                        <li><strong>📊 Market Reports</strong> - Industry trends and statistics</li>
                    </ul>
                    
                    <h4>Qualitative Research:</h4>
                    <ul>
                        <li><strong>🎤 Customer Interviews</strong> - Deep insights into motivations</li>
                        <li><strong>👀 User Observation</strong> - Watch real behavior in context</li>
                        <li><strong>🏢 Focus Groups</strong> - Group dynamics and discussions</li>
                    </ul>
                    
                    <h4>Competitive Analysis:</h4>
                    <ul>
                        <li><strong>🏆 Direct Competitors</strong> - Similar solutions in your space</li>
                        <li><strong>🔄 Indirect Competitors</strong> - Alternative approaches to the problem</li>
                        <li><strong>💰 Pricing Analysis</strong> - Market pricing strategies</li>
                    </ul>
                    
                    <h3>❓ Critical Questions to Answer</h3>
                    <ol>
                        <li><strong>Who:</strong> Who exactly is your ideal customer?</li>
                        <li><strong>What:</strong> What specific problem are you solving?</li>
                        <li><strong>Why:</strong> Why is this problem important to solve now?</li>
                        <li><strong>How Big:</strong> What's the total addressable market (TAM)?</li>
                        <li><strong>How Much:</strong> What would customers pay for your solution?</li>
                        <li><strong>How Reach:</strong> Which channels will you use to reach customers?</li>
                    </ol>
                    
                    <h3>💡 Pro Tips from RK Blueprints</h3>
                    <blockquote>
                        <p><strong>"The biggest mistake entrepreneurs make is asking friends and family for validation. They'll tell you what you want to hear, not what you need to know."</strong></p>
                    </blockquote>
                    
                    <p><strong>Best Practices:</strong></p>
                    <ul>
                        <li>🎯 Always validate with strangers who fit your target market</li>
                        <li>📝 Ask open-ended questions, not leading ones</li>
                        <li>👂 Listen more than you talk (80/20 rule)</li>
                        <li>📊 Look for patterns across multiple data sources</li>
                        <li>🔄 Iterate your approach based on early findings</li>
                    </ul>
                    
                    <h3>📈 Measuring Success</h3>
                    <p>You'll know your research is successful when you can clearly answer:</p>
                    <ul>
                        <li>✅ Your customer's biggest pain point</li>
                        <li>✅ How they currently solve this problem</li>
                        <li>✅ What they'd pay for a better solution</li>
                        <li>✅ Where they go to find solutions</li>
                        <li>✅ Who influences their buying decisions</li>
                    </ul>
                """.trimIndent(),
                duration = 420, // 7 minutes reading time
                orderIndex = 4,
                difficulty = LessonDifficulty.BEGINNER,
                tags = listOf("toolkit", "research-methods", "validation", "customer-interviews"),
                lessonType = LessonType.SUMMARY
            ),
            Lesson(
                id = "npd_003",
                moduleId = "new_product_dev",
                title = "Design Thinking and Prototyping Mastery",
                description = "Master the art of design thinking and create powerful prototypes that bring ideas to life. Learn hands-on prototyping techniques, user testing methods, and iteration strategies used by top product teams.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_003"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_003"] ?: ""),
                duration = 1200, // 20 minutes
                orderIndex = 5,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("design-thinking", "prototyping", "user-testing", "iteration", "user-experience"),
                lessonType = LessonType.VIDEO
            ),
            Lesson(
                id = "npd_004",
                moduleId = "new_product_dev",
                title = "MVP Development and Launch Strategy",
                description = "Build minimum viable products and create successful go-to-market strategies. Learn how to prioritize features, build lean, and launch with maximum impact using proven methodologies.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_004"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_004"] ?: ""),
                duration = 1380, // 23 minutes
                orderIndex = 6,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("mvp", "launch-strategy", "go-to-market", "product-management", "lean-startup"),
                lessonType = LessonType.VIDEO
            ),
            
            // Additional comprehensive lessons for New Product Development
            Lesson(
                id = "npd_005",
                moduleId = "new_product_dev",
                title = "Business Model Canvas and Financial Planning",
                description = "Design sustainable business models and create financial projections that attract investors. Master the Business Model Canvas and learn essential financial planning for product success.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_005"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_005"] ?: ""),
                duration = 1500, // 25 minutes
                orderIndex = 7,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("business-model", "financial-planning", "canvas", "revenue-streams", "investment"),
                lessonType = LessonType.VIDEO
            ),
            
            Lesson(
                id = "npd_006",
                moduleId = "new_product_dev",
                title = "Scaling and Growth Strategies",
                description = "Learn how to scale your product successfully from MVP to market leader. Explore growth hacking techniques, team building strategies, and sustainable scaling methodologies.",
                videoUrl = VideoUrlUpdater.videoUrlMappings["npd_006"] ?: "",
                thumbnailUrl = VideoUrlUpdater.generateThumbnailUrl(VideoUrlUpdater.videoUrlMappings["npd_006"] ?: ""),
                duration = 1680, // 28 minutes
                orderIndex = 8,
                difficulty = LessonDifficulty.ADVANCED,
                tags = listOf("scaling", "growth-hacking", "team-building", "market-expansion", "sustainability"),
                lessonType = LessonType.VIDEO
            ),
            
            // Comprehensive text lesson for design thinking
            Lesson(
                id = "npd_003_text",
                moduleId = "new_product_dev",
                title = "Design Thinking Methodology Deep Dive",
                description = "Complete guide to implementing design thinking in your product development process",
                videoUrl = null,
                thumbnailUrl = null,
                textContent = """
                    <h2>🎨 Design Thinking Methodology Deep Dive</h2>
                    
                    <h3>🧠 The 5-Stage Design Thinking Process</h3>
                    
                    <h4>1. 🤔 Empathize - Understand Your Users</h4>
                    <p><strong>Objective:</strong> Develop deep empathy for your users and their needs.</p>
                    <ul>
                        <li><strong>User Interviews:</strong> Conduct one-on-one conversations</li>
                        <li><strong>Observation:</strong> Watch users in their natural environment</li>
                        <li><strong>Empathy Maps:</strong> Visualize what users think, feel, see, and do</li>
                        <li><strong>Journey Mapping:</strong> Understand the complete user experience</li>
                    </ul>
                    
                    <h4>2. 📝 Define - Frame the Problem</h4>
                    <p><strong>Objective:</strong> Synthesize observations into a clear problem statement.</p>
                    <ul>
                        <li><strong>Point of View Statement:</strong> "User needs a way to... because..."</li>
                        <li><strong>How Might We Questions:</strong> Reframe problems as opportunities</li>
                        <li><strong>Problem Prioritization:</strong> Focus on the most critical issues</li>
                    </ul>
                    
                    <h4>3. 💡 Ideate - Generate Solutions</h4>
                    <p><strong>Objective:</strong> Generate a wide range of creative solutions.</p>
                    <ul>
                        <li><strong>Brainstorming:</strong> Quantity over quality initially</li>
                        <li><strong>Crazy 8s:</strong> 8 ideas in 8 minutes</li>
                        <li><strong>SCAMPER Method:</strong> Substitute, Combine, Adapt, Modify, Put to other uses, Eliminate, Reverse</li>
                        <li><strong>Idea Clustering:</strong> Group similar concepts together</li>
                    </ul>
                    
                    <h4>4. 🔨 Prototype - Build to Think</h4>
                    <p><strong>Objective:</strong> Create tangible representations of your ideas.</p>
                    
                    <p><strong>Prototyping Methods by Fidelity:</strong></p>
                    <ul>
                        <li><strong>📝 Paper Sketches:</strong> Quick, low-cost idea exploration</li>
                        <li><strong>🖼️ Wireframes:</strong> Digital structure and layout</li>
                        <li><strong>🎭 Role Playing:</strong> Act out service experiences</li>
                        <li><strong>📱 Interactive Mockups:</strong> Clickable digital prototypes</li>
                        <li><strong>🏗️ Physical Models:</strong> 3D printed or crafted objects</li>
                    </ul>
                    
                    <h4>5. 🧪 Test - Learn and Iterate</h4>
                    <p><strong>Objective:</strong> Validate assumptions and gather feedback.</p>
                    <ul>
                        <li><strong>Usability Testing:</strong> Observe users interacting with prototypes</li>
                        <li><strong>A/B Testing:</strong> Compare different versions</li>
                        <li><strong>Feedback Collection:</strong> Structured interviews and surveys</li>
                        <li><strong>Iteration Planning:</strong> Incorporate learnings into next version</li>
                    </ul>
                    
                    <h3>🛠️ Essential Prototyping Tools</h3>
                    
                    <h4>Digital Tools:</h4>
                    <ul>
                        <li><strong>Figma/Sketch:</strong> UI/UX design and prototyping</li>
                        <li><strong>InVision/Marvel:</strong> Interactive prototypes</li>
                        <li><strong>Framer:</strong> Advanced interactions and animations</li>
                        <li><strong>Balsamiq:</strong> Quick wireframing</li>
                    </ul>
                    
                    <h4>Physical Tools:</h4>
                    <ul>
                        <li><strong>Post-it Notes:</strong> Rapid ideation and organization</li>
                        <li><strong>Cardboard/Foam:</strong> 3D modeling materials</li>
                        <li><strong>Legos/Play-Doh:</strong> Quick physical mockups</li>
                        <li><strong>3D Printing:</strong> High-fidelity physical prototypes</li>
                    </ul>
                    
                    <h3>💡 RK Blueprints Prototyping Framework</h3>
                    
                    <blockquote>
                        <p><strong>"The goal of prototyping isn't to build the perfect solution—it's to learn as quickly and cheaply as possible."</strong></p>
                    </blockquote>
                    
                    <h4>The 3-2-1 Rule:</h4>
                    <ul>
                        <li><strong>3 Different Concepts:</strong> Explore multiple directions</li>
                        <li><strong>2 Levels of Fidelity:</strong> Start low, increase based on learning</li>
                        <li><strong>1 Clear Hypothesis:</strong> What are you trying to validate?</li>
                    </ul>
                    
                    <h3>🚀 Advanced Techniques</h3>
                    
                    <h4>Service Design:</h4>
                    <ul>
                        <li><strong>Service Blueprints:</strong> Map behind-the-scenes processes</li>
                        <li><strong>Touchpoint Analysis:</strong> Identify all user interactions</li>
                        <li><strong>Ecosystem Mapping:</strong> Understand all stakeholders</li>
                    </ul>
                    
                    <h4>Systems Thinking:</h4>
                    <ul>
                        <li><strong>Root Cause Analysis:</strong> Dig deep into problem sources</li>
                        <li><strong>Leverage Points:</strong> Find high-impact intervention opportunities</li>
                        <li><strong>Feedback Loops:</strong> Understand system dynamics</li>
                    </ul>
                    
                    <h3>📊 Measuring Design Success</h3>
                    <p><strong>Key Metrics to Track:</strong></p>
                    <ul>
                        <li>✅ User satisfaction scores</li>
                        <li>✅ Task completion rates</li>
                        <li>✅ Time to complete tasks</li>
                        <li>✅ Error rates and recovery</li>
                        <li>✅ Conversion and retention rates</li>
                    </ul>
                """.trimIndent(),
                duration = 600, // 10 minutes reading time
                orderIndex = 9,
                difficulty = LessonDifficulty.INTERMEDIATE,
                tags = listOf("design-thinking", "methodology", "prototyping-tools", "user-testing"),
                lessonType = LessonType.CONCEPT_REVIEW
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