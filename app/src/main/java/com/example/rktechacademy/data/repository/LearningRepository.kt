package com.example.rktechacademy.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.rktechacademy.data.database.dao.LearningModuleDao
import com.example.rktechacademy.data.database.dao.LessonDao
import com.example.rktechacademy.data.database.dao.UserProgressDao
import com.example.rktechacademy.data.model.*
import java.util.*

class LearningRepository(
    private val learningModuleDao: LearningModuleDao,
    private val lessonDao: LessonDao,
    private val userProgressDao: UserProgressDao
) {
    
    // Learning Modules
    fun getAllModules(): LiveData<List<LearningModule>> = learningModuleDao.getAllModules()
    
    suspend fun getModuleById(moduleId: String): LearningModule? = 
        learningModuleDao.getModuleById(moduleId)
    
    suspend fun insertModule(module: LearningModule) = 
        learningModuleDao.insertModule(module)
    
    suspend fun insertAllModules(modules: List<LearningModule>) = 
        learningModuleDao.insertAllModules(modules)
    
    // Lessons
    fun getLessonsByModule(moduleId: String): LiveData<List<Lesson>> = 
        lessonDao.getLessonsByModule(moduleId)
    
    suspend fun getLessonById(lessonId: String): Lesson? = 
        lessonDao.getLessonById(lessonId)
    
    suspend fun insertLesson(lesson: Lesson) = 
        lessonDao.insertLesson(lesson)
    
    suspend fun insertAllLessons(lessons: List<Lesson>) = 
        lessonDao.insertAllLessons(lessons)
    
    // User Progress
    suspend fun getProgressByLesson(lessonId: String): UserProgress? = 
        userProgressDao.getProgressByLesson(lessonId)
    
    fun getProgressByLessonLive(lessonId: String): LiveData<UserProgress?> = 
        userProgressDao.getProgressByLessonLive(lessonId)
    
    fun getProgressByModule(moduleId: String): LiveData<List<UserProgress>> = 
        userProgressDao.getProgressByModule(moduleId)
    
    suspend fun markLessonCompleted(lessonId: String) {
        val existingProgress = userProgressDao.getProgressByLesson(lessonId)
        val now = Date()
        
        if (existingProgress != null) {
            userProgressDao.updateProgress(
                existingProgress.copy(
                    isCompleted = true,
                    completedAt = now,
                    lastAccessedAt = now
                )
            )
        } else {
            userProgressDao.insertProgress(
                UserProgress(
                    lessonId = lessonId,
                    isCompleted = true,
                    completedAt = now,
                    startedAt = now,
                    lastAccessedAt = now
                )
            )
        }
    }
    
    suspend fun updateLessonProgress(lessonId: String, watchTimeSeconds: Int, lastPosition: Int) {
        val existingProgress = userProgressDao.getProgressByLesson(lessonId)
        val now = Date()
        
        if (existingProgress != null) {
            userProgressDao.updateProgress(
                existingProgress.copy(
                    watchTimeSeconds = watchTimeSeconds,
                    lastWatchedPosition = lastPosition,
                    lastAccessedAt = now
                )
            )
        } else {
            userProgressDao.insertProgress(
                UserProgress(
                    lessonId = lessonId,
                    watchTimeSeconds = watchTimeSeconds,
                    lastWatchedPosition = lastPosition,
                    startedAt = now,
                    lastAccessedAt = now
                )
            )
        }
    }
    
    // Module Progress Calculation
    suspend fun getModuleProgress(moduleId: String): ModuleProgress {
        val progressList = getProgressByModule(moduleId).value ?: emptyList()
        val totalLessons = lessonDao.getLessonCountByModule(moduleId)
        val completedLessons = progressList.count { it.isCompleted }
        val progressPercentage = if (totalLessons > 0) {
            (completedLessons.toFloat() / totalLessons) * 100f
        } else 0f
        
        return ModuleProgress(
            moduleId = moduleId,
            totalLessons = totalLessons,
            completedLessons = completedLessons,
            progressPercentage = progressPercentage
        )
    }
}