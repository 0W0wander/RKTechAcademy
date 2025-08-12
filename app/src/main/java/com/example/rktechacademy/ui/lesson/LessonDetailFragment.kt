package com.example.rktechacademy.ui.lesson

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.rktechacademy.R
import com.example.rktechacademy.databinding.FragmentLessonDetailBinding
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.LessonType
import com.example.rktechacademy.data.model.UserProgress
import com.example.rktechacademy.ui.home.LearningViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class LessonDetailFragment : Fragment() {

    private var _binding: FragmentLessonDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var learningViewModel: LearningViewModel
    private var exoPlayer: ExoPlayer? = null
    private var currentLesson: Lesson? = null
    private var isCompleted = false
    private var watchTimeJob: Job? = null
    private var hasVideoEnded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        learningViewModel = ViewModelProvider(requireActivity())[LearningViewModel::class.java]
        
        // Get lesson ID from arguments
        val lessonId = arguments?.getString("lesson_id")
        if (lessonId != null) {
            loadLesson(lessonId)
        }
        
        setupClickListeners()
    }

    private fun loadLesson(lessonId: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            val lesson = learningViewModel.getLessonById(lessonId)
            if (lesson == null) {
                Snackbar.make(binding.root, "Lesson not found", Snackbar.LENGTH_SHORT).show()
                return@launch
            }
            // If text lesson, navigate to text fragment
            if (lesson.lessonType != LessonType.VIDEO || lesson.videoUrl.isNullOrEmpty()) {
                val args = Bundle().apply { putString("lesson_id", lesson.id) }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.textLessonFragment, args)
                return@launch
            }
            currentLesson = lesson
            displayLesson(lesson)
            loadProgress(lesson.id)
        }
    }

    private fun displayLesson(lesson: Lesson) {
        binding.lessonTitle.text = lesson.title
        binding.lessonDescription.text = lesson.description
        
        // Format duration
        val minutes = lesson.duration / 60
        binding.lessonDuration.text = getString(R.string.lesson_duration, minutes)
        
        // Set difficulty badge
        binding.difficultyBadge.text = lesson.difficulty.displayName
        binding.difficultyBadge.setBackgroundColor(getDifficultyColor(lesson.difficulty.name))
        
        // Setup video player
        if (!lesson.videoUrl.isNullOrEmpty()) {
            setupVideoPlayer(lesson.videoUrl)
        }
        
        // Add tags as chips
        setupTags(lesson.tags)
    }

    private fun setupVideoPlayer(videoUrl: String) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(requireContext()).build()
            binding.videoPlayerView.player = exoPlayer
        }

        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        exoPlayer?.apply {
            setMediaItem(mediaItem)
            prepare()
            
            // Add listener for tracking progress and auto-completion
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_READY -> {
                            binding.playButton.visibility = View.GONE
                            startWatchTimeTracking()
                        }
                        Player.STATE_ENDED -> {
                            // Video completed - automatically mark as complete
                            if (!isCompleted && !hasVideoEnded) {
                                hasVideoEnded = true
                                autoCompleteVideo()
                            }
                        }
                    }
                }
                
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (isPlaying) {
                        startWatchTimeTracking()
                    } else {
                        stopWatchTimeTracking()
                    }
                }
            })
        }

        binding.playButton.setOnClickListener {
            exoPlayer?.play()
        }
    }

    private fun setupTags(tags: List<String>) {
        binding.tagsChipGroup.removeAllViews()
        tags.forEach { tag ->
            val chip = Chip(requireContext())
            chip.text = tag
            chip.isClickable = false
            binding.tagsChipGroup.addView(chip)
        }
    }

    private fun startWatchTimeTracking() {
        watchTimeJob?.cancel()
        watchTimeJob = lifecycleScope.launch {
            while (isActive && exoPlayer?.isPlaying == true) {
                delay(2000) // Update every 2 seconds
                exoPlayer?.let { player ->
                    currentLesson?.let { lesson ->
                        val currentPosition = (player.currentPosition / 1000).toInt()
                        learningViewModel.updateLessonProgress(lesson.id, currentPosition, currentPosition)
                        updateProgressDisplay(currentPosition, lesson.duration)
                        
                        // Check if 90% watched to suggest completion
                        val watchPercentage = (currentPosition.toFloat() / lesson.duration) * 100
                        if (watchPercentage >= 90 && !isCompleted && !hasVideoEnded) {
                            showNearCompletionMessage()
                        }
                    }
                }
            }
        }
    }

    private fun stopWatchTimeTracking() {
        watchTimeJob?.cancel()
    }

    private fun autoCompleteVideo() {
        currentLesson?.let { lesson ->
            learningViewModel.markLessonCompleted(lesson.id)
            showVideoCompletionCelebration()
            
            Snackbar.make(
                binding.root,
                "🎉 " + getString(R.string.video_completed),
                Snackbar.LENGTH_LONG
            )
            .setBackgroundTint(resources.getColor(R.color.success_green, null))
            .setTextColor(resources.getColor(android.R.color.white, null))
            .setAction("Next Lesson") {
                // TODO: Navigate to next lesson
            }
            .show()
        }
    }

    private fun showVideoCompletionCelebration() {
        // Animate completion checkbox
        binding.completionCheckbox.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
            .setDuration(300)
            .withEndAction {
                binding.completionCheckbox.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(200)
                    .start()
            }
            .start()

        // Animate button
        binding.markCompletedButton.animate()
            .alpha(0.7f)
            .setDuration(200)
            .start()
    }

    private fun showNearCompletionMessage() {
        if (!hasVideoEnded) {
            Snackbar.make(
                binding.root,
                "Almost done! Video will auto-complete when finished.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadProgress(lessonId: String) {
        learningViewModel.getProgressByLessonLive(lessonId).observe(viewLifecycleOwner) { progress: UserProgress? ->
            if (progress != null) {
                isCompleted = progress.isCompleted
                binding.completionCheckbox.isChecked = progress.isCompleted
                
                // Update progress bar
                currentLesson?.let { lesson ->
                    updateProgressDisplay(progress.lastWatchedPosition, lesson.duration)
                }
                
                // Update button text with enhanced styling
                if (progress.isCompleted) {
                    binding.markCompletedButton.text = "✅ Completed"
                    binding.markCompletedButton.isEnabled = false
                    binding.markCompletedButton.alpha = 0.7f
                } else {
                    binding.markCompletedButton.text = getString(R.string.mark_completed)
                    binding.markCompletedButton.isEnabled = true
                    binding.markCompletedButton.alpha = 1.0f
                }
            }
        }
    }

    private fun updateProgressDisplay(watchedSeconds: Int, totalSeconds: Int) {
        val progressPercentage = if (totalSeconds > 0) {
            (watchedSeconds.toFloat() / totalSeconds * 100).toInt()
        } else 0
        
        binding.watchProgressBar.progress = progressPercentage
        binding.progressText.text = "$progressPercentage% watched"
    }

    private fun setupClickListeners() {
        binding.completionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            currentLesson?.let { lesson ->
                if (isChecked) {
                    learningViewModel.markLessonCompleted(lesson.id)
                    showCompletionMessage()
                }
            }
        }

        binding.markCompletedButton.setOnClickListener {
            currentLesson?.let { lesson ->
                learningViewModel.markLessonCompleted(lesson.id)
                showCompletionMessage()
            }
        }

        binding.nextLessonButton.setOnClickListener {
            // TODO: Navigate to next lesson
            Snackbar.make(binding.root, "Next lesson feature coming soon!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showCompletionMessage() {
        // Enhanced completion message with celebration
        showVideoCompletionCelebration()
        
        Snackbar.make(
            binding.root, 
            "🎉 " + getString(R.string.lesson_completed) + " Great job!",
            Snackbar.LENGTH_LONG
        )
        .setBackgroundTint(resources.getColor(R.color.success_green, null))
        .setTextColor(resources.getColor(android.R.color.white, null))
        .setAction("Next Lesson") {
            // TODO: Navigate to next lesson
        }
        .show()
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

    // Sample creation removed; lessons now load from database

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
        stopWatchTimeTracking()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopWatchTimeTracking()
        watchTimeJob?.cancel()
        exoPlayer?.release()
        exoPlayer = null
        _binding = null
    }

    companion object {
        fun newInstance(lessonId: String): LessonDetailFragment {
            val fragment = LessonDetailFragment()
            val args = Bundle()
            args.putString("lesson_id", lessonId)
            fragment.arguments = args
            return fragment
        }
    }
}