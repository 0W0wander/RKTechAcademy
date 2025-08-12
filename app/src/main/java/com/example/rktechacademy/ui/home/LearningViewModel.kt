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

    init {
        initializeData()
        observeModulesForProgress()
    }

    private fun initializeData() {
        viewModelScope.launch {
            try {
                // Check if data already exists (use suspend count)
                val count = database.learningModuleDao().getModuleCount()
                if (count == 0) {
                    // Initialize with sample data
                    val initialModules = InitialDataProvider.getInitialModules()
                    val initialLessons = InitialDataProvider.getInitialLessons()
                    
                    repository.insertAllModules(initialModules)
                    repository.insertAllLessons(initialLessons)
                    
                    // Update lesson counts in modules
                    initialModules.forEach { module ->
                        val lessonCount = initialLessons.count { it.moduleId == module.id }
                        repository.insertModule(module.copy(totalLessons = lessonCount))
                    }
                }
                _isDataInitialized.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isDataInitialized.value = false
            }
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
}