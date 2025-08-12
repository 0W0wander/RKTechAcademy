package com.example.rktechacademy.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rktechacademy.data.model.UserProgress

@Dao
interface UserProgressDao {
    
    @Query("SELECT * FROM user_progress WHERE lessonId = :lessonId")
    suspend fun getProgressByLesson(lessonId: String): UserProgress?
    
    @Query("SELECT * FROM user_progress WHERE lessonId = :lessonId")
    fun getProgressByLessonLive(lessonId: String): LiveData<UserProgress?>
    
    @Query("""
        SELECT up.* FROM user_progress up
        INNER JOIN lessons l ON up.lessonId = l.id
        WHERE l.moduleId = :moduleId
        ORDER BY l.orderIndex ASC
    """)
    fun getProgressByModule(moduleId: String): LiveData<List<UserProgress>>
    
    @Query("SELECT * FROM user_progress WHERE isCompleted = 1")
    fun getCompletedLessons(): LiveData<List<UserProgress>>
    
    @Query("""
        SELECT COUNT(*) FROM user_progress up
        INNER JOIN lessons l ON up.lessonId = l.id
        WHERE l.moduleId = :moduleId AND up.isCompleted = 1
    """)
    suspend fun getCompletedLessonsCountByModule(moduleId: String): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgress): Long
    
    @Update
    suspend fun updateProgress(progress: UserProgress): Int
    
    @Delete
    suspend fun deleteProgress(progress: UserProgress): Int
    
    @Query("DELETE FROM user_progress")
    suspend fun deleteAllProgress(): Int
}