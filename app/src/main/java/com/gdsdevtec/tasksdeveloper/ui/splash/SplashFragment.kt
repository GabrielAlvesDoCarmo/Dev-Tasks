package com.gdsdevtec.tasksdeveloper.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentSplashBinding
import com.gdsdevtec.tasksdeveloper.util.nextFragment

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentSplashBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()
    }

    private fun action() = splashDelay {
        nextFragment(R.id.action_splashFragment_to_loginFragment)
    }
    private fun splashDelay(action: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({ action.invoke() }, 3000)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}