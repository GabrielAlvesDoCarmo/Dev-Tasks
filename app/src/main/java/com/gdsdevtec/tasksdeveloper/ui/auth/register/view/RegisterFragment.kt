package com.gdsdevtec.tasksdeveloper.ui.auth.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gdsdevtec.tasksdeveloper.databinding.FragmentRegisterBinding
import com.gdsdevtec.tasksdeveloper.di.ViewModelInjection
import com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel.RegisterActions
import com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel.RegisterViewModel
import com.gdsdevtec.tasksdeveloper.util.hide
import com.gdsdevtec.tasksdeveloper.util.hideKeyboard
import com.gdsdevtec.tasksdeveloper.util.initToolbar
import com.gdsdevtec.tasksdeveloper.util.messageToast
import com.gdsdevtec.tasksdeveloper.util.show

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>(
        factoryProducer = { ViewModelInjection.getFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentRegisterBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
        observers()
    }

    private fun setupFragment() {
        binding.apply {
            initToolbar(includeToolbar.toolbar)
            includeToolbar.toolbar
            btnRegister.setOnClickListener {
                validateRegisterForm()
            }
        }
    }

    private fun validateRegisterForm() {
        hideKeyboard()
        viewModel.submitActions(
            RegisterActions.ValidateFormRegister(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        )
    }

    private fun observers() {
        viewModel.state.observe(viewLifecycleOwner) { registerState ->
            when {
                registerState.isMsgEmailError -> {
                    binding.progressBar.hide()
                    messageToast(registerState.registerUIModel?.msgEmailError)
                }

                registerState.isMsgPasswordError -> {
                    binding.progressBar.hide()
                    messageToast(registerState.registerUIModel?.msgPasswordError)
                }

                registerState.loading -> {
                    binding.progressBar.show()
                }

                registerState.success -> {

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}