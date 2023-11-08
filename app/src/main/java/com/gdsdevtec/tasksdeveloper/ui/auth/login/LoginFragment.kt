package com.gdsdevtec.tasksdeveloper.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentLoginBinding
import com.gdsdevtec.tasksdeveloper.util.nextFragment

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentLoginBinding.inflate(
        inflater,container,false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    private fun setupFragment() {
        binding.apply {
            btnRegister.setOnClickListener {
                nextFragment(R.id.action_loginFragment_to_registerFragment)
            }
            btnRecover.setOnClickListener {
                nextFragment(R.id.action_loginFragment_to_recoverAccountFragment)
            }
            btnLogin.setOnClickListener {
                nextFragment(R.id.action_loginFragment_to_tasksFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}