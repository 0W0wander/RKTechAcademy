package com.example.rktechacademy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rktechacademy.R
import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.data.model.ModuleProgress
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class LearningModuleAdapter(
    private val onModuleClick: (LearningModule) -> Unit,
    private val onContinueClick: (LearningModule) -> Unit
) : RecyclerView.Adapter<LearningModuleAdapter.ModuleViewHolder>() {

    private var modules = listOf<LearningModule>()
    private var progressMap = mapOf<String, ModuleProgress>()

    fun submitList(newModules: List<LearningModule>) {
        modules = newModules
        notifyDataSetChanged()
    }

    fun updateProgress(moduleProgressMap: Map<String, ModuleProgress>) {
        progressMap = moduleProgressMap
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_learning_module, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = modules[position]
        val progress = progressMap[module.id]
        holder.bind(module, progress)
    }

    override fun getItemCount(): Int = modules.size

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moduleIcon: View = itemView.findViewById(R.id.moduleIcon)
        private val moduleTitle: TextView = itemView.findViewById(R.id.moduleTitle)
        private val moduleDescription: TextView = itemView.findViewById(R.id.moduleDescription)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        private val progressText: TextView = itemView.findViewById(R.id.progressText)
        private val lessonCount: TextView = itemView.findViewById(R.id.lessonCount)
        private val continueButton: MaterialButton = itemView.findViewById(R.id.continueButton)

        fun bind(module: LearningModule, progress: ModuleProgress?) {
            moduleTitle.text = module.title
            moduleDescription.text = module.description

            // Set progress
            if (progress != null) {
                progressBar.progress = progress.progressPercentage.toInt()
                progressText.text = "${progress.completedLessons}/${progress.totalLessons} lessons"
                lessonCount.text = itemView.context.getString(R.string.lessons_count, progress.totalLessons)

                // Update continue button text based on progress
                continueButton.text = if (progress.completedLessons > 0) {
                    itemView.context.getString(R.string.continue_learning)
                } else {
                    "Start Learning"
                }
            } else {
                progressBar.progress = 0
                progressText.text = "0/0 lessons"
                lessonCount.text = itemView.context.getString(R.string.lessons_count, module.totalLessons)
                continueButton.text = "Start Learning"
            }

            // Set module color (you can customize this based on module.color)
            try {
                val color = android.graphics.Color.parseColor(module.color)
                moduleIcon.backgroundTintList = android.content.res.ColorStateList.valueOf(color)
            } catch (e: Exception) {
                // Fallback to default color
            }

            // Click listeners
            itemView.setOnClickListener {
                onModuleClick(module)
            }

            continueButton.setOnClickListener {
                onContinueClick(module)
            }
        }
    }
}