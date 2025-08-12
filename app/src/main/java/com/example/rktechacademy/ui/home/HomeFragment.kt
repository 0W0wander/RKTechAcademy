package com.example.rktechacademy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rktechacademy.R
import com.example.rktechacademy.databinding.FragmentHomeBinding
import com.example.rktechacademy.data.model.LearningModule
import com.example.rktechacademy.ui.adapter.LearningModuleAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var learningViewModel: LearningViewModel
    private lateinit var moduleAdapter: LearningModuleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        learningViewModel = ViewModelProvider(requireActivity())[LearningViewModel::class.java]
        
        setupRecyclerView()
        observeData()
        setupClickListeners()

        // If navigated from drawer to a specific module, open its lesson list
        val moduleIdArg = arguments?.getString("moduleId") ?: arguments?.getString("module_id")
        if (!moduleIdArg.isNullOrEmpty()) {
            val args = Bundle().apply { putString("module_id", moduleIdArg) }
            requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                .navigate(R.id.lessonListFragment, args)
            // Clear the argument to avoid re-navigation on config changes
            arguments?.remove("moduleId")
            arguments?.remove("module_id")
        }
    }

    private fun setupRecyclerView() {
        moduleAdapter = LearningModuleAdapter(
            onModuleClick = { module -> navigateToModule(module) },
            onContinueClick = { module -> continueModule(module) }
        )
        
        binding.modulesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moduleAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        // Observe learning modules
        learningViewModel.allModules.observe(viewLifecycleOwner) { modules ->
            moduleAdapter.submitList(modules)
        }

        // Observe module progress
        learningViewModel.moduleProgress.observe(viewLifecycleOwner) { progressMap ->
            moduleAdapter.updateProgress(progressMap)
        }

        // Observe total progress for summary card
        learningViewModel.getTotalProgress().observe(viewLifecycleOwner) { (completed, total) ->
            binding.totalProgressText.text = "$completed/$total"
        }

        // Observe last accessed lesson for continue learning
        learningViewModel.getLastAccessedLesson().observe(viewLifecycleOwner) { lastLesson ->
            if (lastLesson != null) {
                binding.continueSection.visibility = View.VISIBLE
                binding.lastLessonTitle.text = "Continue: ${lastLesson.title}"
                // You would need to get the module name here
                binding.lastLessonModule.text = "Loading module..."
            } else {
                binding.continueSection.visibility = View.GONE
            }
        }

        // Observe data initialization
        learningViewModel.isDataInitialized.observe(viewLifecycleOwner) { isInitialized ->
            if (isInitialized) {
                // Data is ready, you could hide loading indicators here
            } else {
                // Show error message or retry option
                Snackbar.make(binding.root, "Failed to load data. Please try again.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.continueButton?.setOnClickListener {
            // Handle continue learning click
            showTemporaryMessage("Continue learning feature coming soon!")
        }

        binding.lastLessonCard?.setOnClickListener {
            // Handle last lesson card click
            showTemporaryMessage("Continue learning feature coming soon!")
        }
    }

    private fun navigateToModule(module: LearningModule) {
        // For now, open the first lesson in the module if exists
        learningViewModel.getLessonsByModule(module.id).observe(viewLifecycleOwner) { lessons ->
            if (!lessons.isNullOrEmpty()) {
                val first = lessons.minByOrNull { it.orderIndex } ?: lessons.first()
                val args = Bundle().apply { putString("lesson_id", first.id) }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.lessonDetailFragment, args)
            } else {
                showTemporaryMessage("No lessons yet in ${module.title}")
            }
        }
    }

    private fun continueModule(module: LearningModule) {
        // Open next incomplete lesson (fallback to first)
        learningViewModel.getLessonsByModule(module.id).observe(viewLifecycleOwner) { lessons ->
            if (!lessons.isNullOrEmpty()) {
                val lesson = lessons.first() // Simplified; could use progress to pick next
                val args = Bundle().apply { putString("lesson_id", lesson.id) }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.lessonDetailFragment, args)
            } else {
                showTemporaryMessage("No lessons yet in ${module.title}")
            }
        }
    }

    private fun showTemporaryMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}