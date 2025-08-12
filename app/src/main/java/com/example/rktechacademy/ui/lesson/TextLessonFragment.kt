package com.example.rktechacademy.ui.lesson

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rktechacademy.R
import com.example.rktechacademy.data.model.LessonType
import com.example.rktechacademy.data.repository.LearningRepository
import com.example.rktechacademy.databinding.FragmentTextLessonBinding
import com.example.rktechacademy.ui.home.LearningViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment for displaying text-based lessons with enhanced progress tracking
 * Features:
 * - Formatted text content display
 * - Automatic progress tracking
 * - Completion checkmarks
 * - Smooth navigation to next lesson
 */
class TextLessonFragment : Fragment() {

    private var _binding: FragmentTextLessonBinding? = null
    private val binding get() = _binding!!

    private var lessonId: String? = null
    private lateinit var viewModel: LearningViewModel
    private var isCompleted = false
    private var hasStartedReading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[LearningViewModel::class.java]

        // Resolve lessonId from arguments (support both keys)
        lessonId = arguments?.getString("lesson_id") ?: arguments?.getString("lessonId")

        setupLesson()
        observeProgress()
        setupClickListeners()
        startReadingTimer()
    }

    private fun setupLesson() {
        val lessonId = lessonId
        
        // Fetch lesson details (in a real app, this would come from ViewModel)
        // For now, we'll use placeholder data
        binding.lessonTitle.text = "Sample Text Lesson"
        binding.lessonDescription.text = "This is a sample text lesson description"
        binding.readingTime.text = "3 min read"
        
        // Set lesson type badge
        binding.lessonTypeBadge.text = getString(R.string.text_lesson)
        
        // Sample formatted text content (you would get this from the lesson data)
        val sampleContent = """
            <h2>Key Learning Points</h2>
            
            <p><strong>Understanding Innovation:</strong></p>
            <ul>
                <li>Innovation is about creating value for users</li>
                <li>Focus on solving real problems</li>
                <li>Iterate based on feedback</li>
            </ul>
            
            <p><strong>Implementation Strategy:</strong></p>
            <ol>
                <li>Start with user research</li>
                <li>Define the problem clearly</li>
                <li>Build minimal solutions first</li>
                <li>Test and refine continuously</li>
            </ol>
            
            <p><em>Remember:</em> The best innovations often come from understanding user pain points deeply and creating elegant solutions.</p>
        """.trimIndent()
        
        binding.textContent.text = Html.fromHtml(sampleContent, Html.FROM_HTML_MODE_COMPACT)
    }

    private fun observeProgress() {
        // Observe lesson completion status
        lessonId?.let { observedLessonId ->
            viewModel.getProgressByLessonLive(observedLessonId).observe(viewLifecycleOwner) { progress: com.example.rktechacademy.data.model.UserProgress? ->
                progress?.let { p ->
                    isCompleted = p.isCompleted
                    updateCompletionUI()
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.markCompletedButton.setOnClickListener {
            markLessonCompleted()
        }

        binding.nextLessonButton.setOnClickListener {
            navigateToNextLesson()
        }

        // Track reading progress by detecting scroll
        binding.root.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (!hasStartedReading && scrollY > 100) {
                hasStartedReading = true
                // Track that user has started reading
                updateReadingProgress()
            }
        }
    }

    private fun startReadingTimer() {
        // Auto-complete after a reasonable reading time (for demo purposes)
        binding.root.postDelayed({
            if (!isCompleted && hasStartedReading) {
                showAutoCompleteOption()
            }
        }, 10000) // 10 seconds for demo, in real app this would be based on content length
    }

    private fun markLessonCompleted() {
        if (!isCompleted) {
            lessonId?.let { id -> viewModel.markLessonCompleted(id) }
            showCompletionAnimation()
            
            // Show success message
            Snackbar.make(binding.root, getString(R.string.text_lesson_completed), Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.success_green, null))
                .setTextColor(resources.getColor(android.R.color.white, null))
                .setAction("Next Lesson") {
                    navigateToNextLesson()
                }
                .show()
        }
    }

    private fun updateCompletionUI() {
        if (isCompleted) {
            binding.completionStatus.setImageResource(R.drawable.ic_checkmark_filled)
            binding.completionStatus.alpha = 1.0f
            binding.markCompletedButton.text = getString(R.string.completed_checkmark) + " Completed"
            binding.markCompletedButton.isEnabled = false
            binding.markCompletedButton.alpha = 0.7f
            
            // Show progress indicator
            binding.progressIndicator.visibility = View.VISIBLE
            binding.progressMessage.text = getString(R.string.great_job)
        } else {
            binding.completionStatus.setImageResource(R.drawable.ic_checkmark_empty)
            binding.completionStatus.alpha = 0.5f
            binding.markCompletedButton.text = getString(R.string.mark_completed)
            binding.markCompletedButton.isEnabled = true
            binding.markCompletedButton.alpha = 1.0f
            binding.progressIndicator.visibility = View.GONE
        }
    }

    private fun showCompletionAnimation() {
        // Animate the completion checkmark
        binding.completionStatus.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
            .setDuration(200)
            .withEndAction {
                binding.completionStatus.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(200)
                    .start()
            }
            .start()

        // Show progress indicator with animation
        binding.progressIndicator.visibility = View.VISIBLE
        binding.progressIndicator.alpha = 0f
        binding.progressIndicator.animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }

    private fun showAutoCompleteOption() {
        Snackbar.make(binding.root, "Finished reading? Mark as complete!", Snackbar.LENGTH_LONG)
            .setAction("Complete") {
                markLessonCompleted()
            }
            .show()
    }

    private fun updateReadingProgress() {
        // In a real app, you might track reading progress to the backend
        // For now, we'll just mark that reading has started
    }

    private fun navigateToNextLesson() {
        // Navigate to the next lesson (this would require navigation setup)
        // For now, we'll just go back to the module
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Navigation args class (would be generated by Safe Args)
data class TextLessonFragmentArgs(val lessonId: String)