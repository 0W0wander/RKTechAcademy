package com.example.rktechacademy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rktechacademy.data.database.dao.LearningModuleDao
import com.example.rktechacademy.data.database.dao.LessonDao
import com.example.rktechacademy.data.database.dao.UserProgressDao
import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.UserProgress

@Database(
    entities = [LearningModule::class, Lesson::class, UserProgress::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RKTechAcademyDatabase : RoomDatabase() {
    
    abstract fun learningModuleDao(): LearningModuleDao
    abstract fun lessonDao(): LessonDao
    abstract fun userProgressDao(): UserProgressDao
    
    companion object {
        @Volatile
        private var INSTANCE: RKTechAcademyDatabase? = null
        
        fun getDatabase(context: Context): RKTechAcademyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RKTechAcademyDatabase::class.java,
                    "rk_tech_academy_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}