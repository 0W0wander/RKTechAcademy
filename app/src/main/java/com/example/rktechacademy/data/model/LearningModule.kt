package com.example.rktechacademy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learning_modules")
data class LearningModule(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val iconResource: String,
    val color: String,
    val orderIndex: Int,
    val totalLessons: Int = 0
)

enum class ModuleType(val displayName: String) {
    NEW_PRODUCT_DEVELOPMENT("New Product Development"),
    TECHNICAL_SOLUTIONS("Technical Solutions"),
    HR_IT_SOLUTIONS("HR & IT Solutions"),
    LIVE_TALENT("Live Talent"),
    RKB_LABS("RKB Labs")
}