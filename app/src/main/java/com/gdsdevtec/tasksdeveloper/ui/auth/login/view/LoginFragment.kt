package com.gdsdevtec.tasksdeveloper.ui.auth.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentLoginBinding
import com.gdsdevtec.tasksdeveloper.di.ViewModelInjection
import com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel.LoginAction
import com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel.LoginState
import com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel.LoginViewModel
import com.gdsdevtec.tasksdeveloper.util.hide
import com.gdsdevtec.tasksdeveloper.util.hideKeyboard
import com.gdsdevtec.tasksdeveloper.util.nextFragment
import com.gdsdevtec.tasksdeveloper.util.show
import com.gdsdevtec.tasksdeveloper.util.showBottomSheet

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels(
        factoryProducer = {
            ViewModelInjection.getFactory()
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentLoginBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
        observers()
    }

    private fun observers() {
        viewModel.state.observe(viewLifecycleOwner) { loginState ->
            when {
                loginState.isEmailError -> emailError(loginState)
                loginState.isPasswordError -> passwordError(loginState)
                loginState.loading -> binding.progressBar.show()
                loginState.success -> success()
            }
        }
    }

    private fun success() {
        binding.progressBar.hide()
        nextFragment(R.id.action_global_homeFragment)
    }

    private fun emailError(loginState: LoginState) {
        binding.progressBar.hide()
        loginState.loginUiModel?.msgErrorEmail?.let {
            showBottomSheet(message = it)
        }
    }

    private fun passwordError(loginState: LoginState) {
        binding.progressBar.hide()
        loginState.loginUiModel?.msgErrorPassword?.let {
            showBottomSheet(message = it)
        }
    }

    private fun setupFragment() {
        binding.apply {
            btnRegister.setOnClickListener {
                nextFragment(R.id.action_loginFragment_to_registerFragment)
            }
            btnRecover.setOnClickListener {
                nextFragment(R.id.action_loginFragment_to_recoverAccountFragment)
            }
            btnLogin.setOnClickListener { validateForm() }
        }
    }

    private fun validateForm() {
        hideKeyboard()
        viewModel.submitAction(
            LoginAction.ValidateForm(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}