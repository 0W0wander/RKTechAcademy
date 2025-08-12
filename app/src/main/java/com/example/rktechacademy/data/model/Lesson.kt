package com.example.rktechacademy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "lessons",
    foreignKeys = [ForeignKey(
        entity = LearningModule::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("moduleId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["moduleId"])]
)
data class Lesson(
    @PrimaryKey
    val id: String,
    val moduleId: String,
    val title: String,
    val description: String,
    val videoUrl: String?,
    val thumbnailUrl: String?,
    val textContent: String? = null, // For text-based lessons
    val duration: Int, // in seconds (for videos) or estimated reading time (for text)
    val orderIndex: Int,
    val difficulty: LessonDifficulty = LessonDifficulty.BEGINNER,
    val tags: List<String> = emptyList(),
    val lessonType: LessonType = LessonType.VIDEO
)

enum class LessonDifficulty(val displayName: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    EXPERT("Expert")
}

enum class LessonType(val displayName: String) {
    VIDEO("Video Lesson"),
    TEXT("Text Lesson"),
    CONCEPT_REVIEW("Concept Review"),
    SUMMARY("Quick Summary")
}