package com.gdsdevtec.tasksdeveloper.ui.home.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentTodoBinding
import com.gdsdevtec.tasksdeveloper.util.nextFragment

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentTodoBinding.inflate(
        inflater,container,false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    private fun setupFragment() {
        binding.apply {
            fabAdd.setOnClickListener {
                nextFragment(R.id.action_homeFragment_to_formTasksFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}