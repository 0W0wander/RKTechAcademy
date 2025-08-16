package com.example.rktechacademy.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rktechacademy.data.model.Lesson

@Dao
interface LessonDao {
    
    @Query("SELECT * FROM lessons WHERE moduleId = :moduleId ORDER BY orderIndex ASC")
    fun getLessonsByModule(moduleId: String): LiveData<List<Lesson>>
    
    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    suspend fun getLessonById(lessonId: String): Lesson?
    
    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    fun getAllLessons(): LiveData<List<Lesson>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLessons(lessons: List<Lesson>): List<Long>
    
    @Update
    suspend fun updateLesson(lesson: Lesson): Int
    
    @Delete
    suspend fun deleteLesson(lesson: Lesson): Int
    
    @Query("DELETE FROM lessons WHERE moduleId = :moduleId")
    suspend fun deleteLessonsByModule(moduleId: String): Int
    
    @Query("SELECT COUNT(*) FROM lessons WHERE moduleId = :moduleId")
    suspend fun getLessonCountByModule(moduleId: String): Int
    
    @Query("DELETE FROM lessons")
    suspend fun deleteAllLessons(): Int
}