package com.example.rktechacademy.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rktechacademy.R
import com.example.rktechacademy.data.model.Lesson
import com.example.rktechacademy.data.model.LessonType
import com.example.rktechacademy.databinding.FragmentLessonListBinding
import com.example.rktechacademy.ui.home.LearningViewModel
import com.example.rktechacademy.ui.adapter.LessonAdapter

class LessonListFragment : Fragment() {

    private var _binding: FragmentLessonListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LearningViewModel
    private lateinit var adapter: LessonAdapter

    private var moduleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LearningViewModel::class.java]
        moduleId = arguments?.getString("module_id") ?: arguments?.getString("moduleId")

        setupRecycler()
        observeLessons()
    }

    private fun setupRecycler() {
        adapter = LessonAdapter(
            onLessonClick = { openLesson(it) },
            onWatchClick = { openLesson(it) },
            onCompletionToggle = { lesson, isChecked ->
                if (isChecked) viewModel.markLessonCompleted(lesson.id)
            }
        )
        binding.lessonRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.lessonRecyclerView.adapter = adapter
    }

    private fun observeLessons() {
        val id = moduleId ?: return
        viewModel.getLessonsByModule(id).observe(viewLifecycleOwner) { lessons ->
            adapter.submitList(lessons)
        }
    }

    private fun openLesson(lesson: Lesson) {
        val args = bundleOf("lesson_id" to lesson.id)
        val navController = requireActivity().findNavController(R.id.nav_host_fragment_content_main)
        if (lesson.lessonType == LessonType.VIDEO && !lesson.videoUrl.isNullOrEmpty()) {
            navController.navigate(R.id.lessonDetailFragment, args)
        } else {
            navController.navigate(R.id.textLessonFragment, args)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


