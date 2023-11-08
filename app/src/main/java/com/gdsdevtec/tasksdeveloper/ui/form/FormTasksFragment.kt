package com.gdsdevtec.tasksdeveloper.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.databinding.FragmentFormTasksBinding
import com.gdsdevtec.tasksdeveloper.util.initToolbar

class FormTasksFragment : Fragment() {
    private var _binding: FragmentFormTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentFormTasksBinding.inflate(
        inflater,container,false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.includeToolbar.toolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}