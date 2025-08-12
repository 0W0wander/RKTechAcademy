package com.example.rktechacademy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rktechacademy.R
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.UserProgress
import com.google.android.material.button.MaterialButton

class LessonAdapter(
    private val onLessonClick: (Lesson) -> Unit,
    private val onWatchClick: (Lesson) -> Unit,
    private val onCompletionToggle: (Lesson, Boolean) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var lessons = listOf<Lesson>()
    private var progressMap = mapOf<String, UserProgress>()

    fun submitList(newLessons: List<Lesson>) {
        lessons = newLessons
        notifyDataSetChanged()
    }

    fun updateProgress(lessonProgressMap: Map<String, UserProgress>) {
        progressMap = lessonProgressMap
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        val progress = progressMap[lesson.id]
        holder.bind(lesson, progress)
    }

    override fun getItemCount(): Int = lessons.size

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val completionCheckbox: CheckBox = itemView.findViewById(R.id.completionCheckbox)
        private val lessonTitle: TextView = itemView.findViewById(R.id.lessonTitle)
        private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDescription)
        private val lessonDuration: TextView = itemView.findViewById(R.id.lessonDuration)
        private val difficultyBadge: TextView = itemView.findViewById(R.id.difficultyBadge)
        private val watchButton: MaterialButton = itemView.findViewById(R.id.watchButton)

        fun bind(lesson: Lesson, progress: UserProgress?) {
            lessonTitle.text = lesson.title
            lessonDescription.text = lesson.description
            
            // Format duration
            val minutes = lesson.duration / 60
            lessonDuration.text = itemView.context.getString(R.string.lesson_duration, minutes)
            
            // Set difficulty badge
            difficultyBadge.text = lesson.difficulty.displayName
            difficultyBadge.setBackgroundColor(getDifficultyColor(lesson.difficulty.name))
            
            // Set completion status
            val isCompleted = progress?.isCompleted ?: false
            completionCheckbox.isChecked = isCompleted
            
            // Update watch button text based on progress
            if (isCompleted) {
                watchButton.text = "Review"
                lessonTitle.alpha = 0.7f
                lessonDescription.alpha = 0.7f
            } else if (progress != null && progress.lastWatchedPosition > 0) {
                watchButton.text = "Continue"
                lessonTitle.alpha = 1.0f
                lessonDescription.alpha = 1.0f
            } else {
                watchButton.text = itemView.context.getString(R.string.watch_lesson)
                lessonTitle.alpha = 1.0f
                lessonDescription.alpha = 1.0f
            }

            // Click listeners
            itemView.setOnClickListener {
                onLessonClick(lesson)
            }

            watchButton.setOnClickListener {
                onWatchClick(lesson)
            }

            completionCheckbox.setOnCheckedChangeListener { _, isChecked ->
                onCompletionToggle(lesson, isChecked)
            }
        }

        private fun getDifficultyColor(difficulty: String): Int {
            return when (difficulty) {
                "BEGINNER" -> android.graphics.Color.parseColor("#1E3A8A")
                "INTERMEDIATE" -> android.graphics.Color.parseColor("#3B82F6")
                "ADVANCED" -> android.graphics.Color.parseColor("#F44336")
                "EXPERT" -> android.graphics.Color.parseColor("#9C27B0")
                else -> android.graphics.Color.parseColor("#757575")
            }
        }
    }
}