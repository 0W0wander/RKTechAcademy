package com.example.rktechacademy.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.rktechacademy.data.InitialDataProvider
import com.example.rktechacademy.data.database.RKTechAcademyDatabase
import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.ModuleProgress
import com.example.rktechacademy.data.model.UserProgress
import com.example.rktechacademy.data.repository.LearningRepository
import kotlinx.coroutines.launch

class LearningViewModel(application: Application) : AndroidViewModel(application) {

    private val database = RKTechAcademyDatabase.getDatabase(application)
    private val repository = LearningRepository(
        database.learningModuleDao(),
        database.lessonDao(),
        database.userProgressDao()
    )

    val allModules: LiveData<List<LearningModule>> = repository.getAllModules()

    private val _isDataInitialized = MutableLiveData<Boolean>()
    val isDataInitialized: LiveData<Boolean> = _isDataInitialized

    private val _moduleProgress = MutableLiveData<Map<String, ModuleProgress>>()
    val moduleProgress: LiveData<Map<String, ModuleProgress>> = _moduleProgress
    
    private val _streakDays = MutableLiveData<Int>(0)
    val streakDays: LiveData<Int> = _streakDays

    init {
        initializeData()
        observeModulesForProgress()
    }

    private fun initializeData() {
        viewModelScope.launch {
            try {
                // Always refresh data to ensure new lessons are loaded
                refreshAllData()
                _isDataInitialized.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isDataInitialized.value = false
            }
        }
    }
    
    private suspend fun refreshAllData() {
        // Clear existing data completely
        database.userProgressDao().deleteAllProgress()
        database.lessonDao().deleteAllLessons()
        database.learningModuleDao().deleteAllModules()
        
        // Insert fresh data
        val initialModules = InitialDataProvider.getInitialModules()
        val initialLessons = InitialDataProvider.getInitialLessons()
        
        android.util.Log.d("LearningViewModel", "Inserting ${initialModules.size} modules and ${initialLessons.size} lessons")
        
        repository.insertAllModules(initialModules)
        repository.insertAllLessons(initialLessons)
        
        // Debug: Print lesson details
        initialLessons.forEach { lesson ->
            android.util.Log.d("LearningViewModel", "Lesson: ${lesson.title} - Module: ${lesson.moduleId}")
        }
        
        // Update lesson counts in modules
        initialModules.forEach { module ->
            val lessonCount = initialLessons.count { it.moduleId == module.id }
            android.util.Log.d("LearningViewModel", "Module ${module.id} has $lessonCount lessons")
            repository.insertModule(module.copy(totalLessons = lessonCount))
        }
    }

    private fun observeModulesForProgress() {
        allModules.observeForever { modules ->
            if (modules != null) {
                updateModuleProgress(modules)
            }
        }
    }

    private fun updateModuleProgress(modules: List<LearningModule>) {
        viewModelScope.launch {
            val progressMap = mutableMapOf<String, ModuleProgress>()
            
            modules.forEach { module ->
                try {
                    val totalLessons = database.lessonDao().getLessonCountByModule(module.id)
                    val completedLessons = database.userProgressDao().getCompletedLessonsCountByModule(module.id)
                    val progressPercentage = if (totalLessons > 0) {
                        (completedLessons.toFloat() / totalLessons) * 100f
                    } else 0f

                    progressMap[module.id] = ModuleProgress(
                        moduleId = module.id,
                        totalLessons = totalLessons,
                        completedLessons = completedLessons,
                        progressPercentage = progressPercentage
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            
            _moduleProgress.value = progressMap
            updateStreak()
        }
    }
    
    private suspend fun calculateStreakDays(dates: List<java.util.Date>): Int {
        if (dates.isEmpty()) return 0
        val millisInDay = 24L * 60L * 60L * 1000L
        val uniqueDays = dates.map { it.time / millisInDay }.toSortedSet().toList().sortedDescending()
        var streak = 1
        for (i in 1 until uniqueDays.size) {
            if (uniqueDays[i] == uniqueDays[i - 1] - 1) {
                streak += 1
            } else if (uniqueDays[i] == uniqueDays[i - 1]) {
                // same day, ignore
            } else {
                break
            }
        }
        return streak
    }
    
    private fun updateStreak() {
        viewModelScope.launch {
            val dates = database.userProgressDao().getAllAccessDates()
            val streak = calculateStreakDays(dates)
            _streakDays.postValue(streak)
        }
    }

    fun getLessonsByModule(moduleId: String): LiveData<List<Lesson>> {
        return repository.getLessonsByModule(moduleId)
    }

    fun markLessonCompleted(lessonId: String) {
        viewModelScope.launch {
            try {
                repository.markLessonCompleted(lessonId)
                // Refresh progress after marking completion
                allModules.value?.let { updateModuleProgress(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateLessonProgress(lessonId: String, watchTimeSeconds: Int, lastPosition: Int) {
        viewModelScope.launch {
            try {
                repository.updateLessonProgress(lessonId, watchTimeSeconds, lastPosition)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getLessonById(lessonId: String): Lesson? {
        return repository.getLessonById(lessonId)
    }

    fun getProgressByLessonLive(lessonId: String): LiveData<UserProgress?> {
        return repository.getProgressByLessonLive(lessonId)
    }

    fun getTotalProgress(): LiveData<Pair<Int, Int>> {
        return moduleProgress.map { progressMap ->
            val totalLessons = progressMap.values.sumOf { it.totalLessons }
            val completedLessons = progressMap.values.sumOf { it.completedLessons }
            Pair(completedLessons, totalLessons)
        }
    }

    // Get the most recently accessed lesson for "Continue Learning" feature
    fun getLastAccessedLesson(): LiveData<Lesson?> {
        return liveData {
            try {
                // This would require a more complex query to get the most recent lesson
                // For now, we'll emit null and implement this later
                emit(null)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }
    
    // Debug method to force refresh data
    fun forceRefreshData() {
        viewModelScope.launch {
            try {
                refreshAllData()
                android.util.Log.d("LearningViewModel", "Force refresh completed")
            } catch (e: Exception) {
                android.util.Log.e("LearningViewModel", "Force refresh failed", e)
            }
        }
    }
}