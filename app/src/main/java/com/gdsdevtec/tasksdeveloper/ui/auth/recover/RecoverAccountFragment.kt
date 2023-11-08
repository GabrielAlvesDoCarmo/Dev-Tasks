package com.gdsdevtec.tasksdeveloper.ui.auth.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.databinding.FragmentRecoverAccountBinding
import com.gdsdevtec.tasksdeveloper.util.initToolbar

class RecoverAccountFragment : Fragment() {


    private var _binding : FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentRecoverAccountBinding.inflate(
        inflater,container,false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            initToolbar(includeToolbar.toolbar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}