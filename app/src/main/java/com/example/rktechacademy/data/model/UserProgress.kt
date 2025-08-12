package com.example.rktechacademy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.Date

@Entity(
    tableName = "user_progress",
    foreignKeys = [ForeignKey(
        entity = Lesson::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("lessonId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class UserProgress(
    @PrimaryKey 
    val lessonId: String,
    val isCompleted: Boolean = false,
    val watchTimeSeconds: Int = 0,
    val lastWatchedPosition: Int = 0, // in seconds
    val completedAt: Date? = null,
    val startedAt: Date = Date(),
    val lastAccessedAt: Date = Date()
)

data class ModuleProgress(
    val moduleId: String,
    val totalLessons: Int,
    val completedLessons: Int,
    val progressPercentage: Float
) {
    val isCompleted: Boolean
        get() = completedLessons == totalLessons
}