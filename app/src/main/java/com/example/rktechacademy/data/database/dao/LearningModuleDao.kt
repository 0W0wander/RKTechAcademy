package com.example.rktechacademy.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rktechacademy.data.model.LearningModule

@Dao
interface LearningModuleDao {
    
    @Query("SELECT * FROM learning_modules ORDER BY orderIndex ASC")
    fun getAllModules(): LiveData<List<LearningModule>>
    
    @Query("SELECT COUNT(*) FROM learning_modules")
    suspend fun getModuleCount(): Int
    
    @Query("SELECT * FROM learning_modules WHERE id = :moduleId")
    suspend fun getModuleById(moduleId: String): LearningModule?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModule(module: LearningModule): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllModules(modules: List<LearningModule>): List<Long>
    
    @Update
    suspend fun updateModule(module: LearningModule): Int
    
    @Delete
    suspend fun deleteModule(module: LearningModule): Int
    
    @Query("DELETE FROM learning_modules")
    suspend fun deleteAllModules(): Int
}