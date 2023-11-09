package com.gdsdevtec.tasksdeveloper.ui.auth.recover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gdsdevtec.tasksdeveloper.databinding.FragmentRecoverAccountBinding
import com.gdsdevtec.tasksdeveloper.di.ViewModelInjection
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel.RecoverAction
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel.RecoverState
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel.RecoverViewModel
import com.gdsdevtec.tasksdeveloper.util.hide
import com.gdsdevtec.tasksdeveloper.util.hideKeyboard
import com.gdsdevtec.tasksdeveloper.util.initToolbar
import com.gdsdevtec.tasksdeveloper.util.messageToast
import com.gdsdevtec.tasksdeveloper.util.popScreen
import com.gdsdevtec.tasksdeveloper.util.show
import com.gdsdevtec.tasksdeveloper.util.showBottomSheet

class RecoverAccountFragment : Fragment() {


    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RecoverViewModel>(
        factoryProducer = {
            ViewModelInjection.getFactory()
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentRecoverAccountBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
        observers()
    }

    private fun observers() {
        viewModel.state.observe(viewLifecycleOwner) { recoverState ->
            when {
                recoverState.isEmailError -> {
                    emailError(recoverState)
                }
                recoverState.loading -> {
                    binding.progressBar.show()
                }
                recoverState.success -> {
                    successRecover(recoverState.recoverUIModel?.msgSuccess)
                }
            }
        }
    }

    private fun successRecover(msgSuccess: Int?) {
        binding.progressBar.hide()
        msgSuccess?.let {
            messageToast(
                String.format(
                    getString(it), binding.edtEmail.text.toString()
                )
            )
        }
        popScreen()
    }

    private fun emailError(recoverState: RecoverState) {
        binding.progressBar.hide()
        recoverState.recoverUIModel?.msgError?.let {
            showBottomSheet(message = it)
        }
    }

    private fun setupFragment() {
        binding.apply {
            initToolbar(includeToolbar.toolbar)
            btnRecover.setOnClickListener {
                validateEmailField()
            }
        }
    }

    private fun validateEmailField() {
        hideKeyboard()
        viewModel.submitActions(
            RecoverAction.ValidateEmailRecover(
                binding.edtEmail.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}