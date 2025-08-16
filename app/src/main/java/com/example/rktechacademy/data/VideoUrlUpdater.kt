package com.example.rktechacademy.data

/**
 * Helper class to easily update video URLs with Raheem's actual YouTube videos
 * 
 * Instructions for updating with Raheem's videos:
 * 1. Go to Raheem's YouTube channel
 * 2. Find the video you want to use for each lesson
 * 3. Copy the YouTube video URL (e.g., https://www.youtube.com/watch?v=VIDEO_ID)
 * 4. Replace the placeholder URLs in InitialDataProvider.kt
 * 
 * YouTube URL Format Examples:
 * - Standard URL: https://www.youtube.com/watch?v=dQw4w9WgXcQ
 * - Embedded URL: https://www.youtube.com/embed/dQw4w9WgXcQ
 * - Thumbnail URL: https://img.youtube.com/vi/dQw4w9WgXcQ/maxresdefault.jpg
 */
object VideoUrlUpdater {
    
    /**
     * Convert YouTube watch URL to embedded format for video player
     * Example: https://www.youtube.com/watch?v=dQw4w9WgXcQ 
     * becomes: https://www.youtube.com/embed/dQw4w9WgXcQ
     */
    fun convertToEmbedUrl(youtubeUrl: String): String {
        return when {
            youtubeUrl.contains("youtube.com/watch?v=") -> {
                val videoId = youtubeUrl.substringAfter("v=").substringBefore("&")
                "https://www.youtube.com/embed/$videoId"
            }
            youtubeUrl.contains("youtu.be/") -> {
                val videoId = youtubeUrl.substringAfter("youtu.be/").substringBefore("?")
                "https://www.youtube.com/embed/$videoId"
            }
            else -> youtubeUrl // Already in correct format or different platform
        }
    }
    
    /**
     * Extract video ID from YouTube URL for thumbnail generation
     */
    fun extractVideoId(youtubeUrl: String): String? {
        return when {
            youtubeUrl.contains("youtube.com/watch?v=") -> {
                youtubeUrl.substringAfter("v=").substringBefore("&")
            }
            youtubeUrl.contains("youtu.be/") -> {
                youtubeUrl.substringAfter("youtu.be/").substringBefore("?")
            }
            youtubeUrl.contains("youtube.com/embed/") -> {
                youtubeUrl.substringAfter("embed/").substringBefore("?")
            }
            else -> null
        }
    }
    
    /**
     * Generate thumbnail URL from YouTube video URL
     */
    fun generateThumbnailUrl(youtubeUrl: String): String {
        val videoId = extractVideoId(youtubeUrl)
        return if (videoId != null) {
            "https://img.youtube.com/vi/$videoId/maxresdefault.jpg"
        } else {
            "https://via.placeholder.com/1280x720/1E3A8A/FFFFFF?text=RK+Tech+Academy"
        }
    }
    
    /**
     * Video URL mapping template for easy updating
     * 
     * TO UPDATE WITH RAHEEM'S VIDEOS:
     * 1. Replace each URL with the actual YouTube video URL
     * 2. The app will automatically handle conversion to embed format
     * 3. Thumbnails will be generated automatically
     */
    val videoUrlMappings = mapOf(
        // New Product Development Module
        "npd_001" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "npd_002" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "npd_003" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        "npd_004" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "npd_005" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
        "npd_006" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
        
        // Technical Solutions Module
        "tech_001" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        "tech_002" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_6
        "tech_003" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_7
        "tech_004" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_8
        
        // HR & IT Solutions Module
        "hr_001" to "",   // REPLACE_WITH_RAHEEM_VIDEO_URL_9
        "hr_002" to "",  // REPLACE_WITH_RAHEEM_VIDEO_URL_10
        "hr_003" to "",  // REPLACE_WITH_RAHEEM_VIDEO_URL_11
        
        // Live Talent Module
        "talent_001" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_12
        "talent_002" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_13
        "talent_003" to "", // REPLACE_WITH_RAHEEM_VIDEO_URL_14
        
        // RKB Labs Module (Hands-on Projects)
        "labs_001" to "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "labs_002" to "",
        "labs_003" to "",
        "labs_004" to ""
    )
    
    /**
     * Example of how to use actual YouTube URLs:
     * 
     * Instead of: "REPLACE_WITH_RAHEEM_VIDEO_URL_1"
     * Use: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
     * 
     * The app will automatically:
     * - Convert to embed format for video player
     * - Generate thumbnail URLs
     * - Handle video loading and playback
     */
}