package com.gdsdevtec.tasksdeveloper.ui.form.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gdsdevtec.tasksdeveloper.databinding.FragmentFormTasksBinding
import com.gdsdevtec.tasksdeveloper.di.ViewModelInjection
import com.gdsdevtec.tasksdeveloper.ui.form.viewmodel.FormTasksAction
import com.gdsdevtec.tasksdeveloper.ui.form.viewmodel.FormTasksViewModel
import com.gdsdevtec.tasksdeveloper.util.hide
import com.gdsdevtec.tasksdeveloper.util.initToolbar
import com.gdsdevtec.tasksdeveloper.util.messageToast
import com.gdsdevtec.tasksdeveloper.util.show

class FormTasksFragment : Fragment() {
    private var _binding: FragmentFormTasksBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FormTasksViewModel>(
        factoryProducer = { ViewModelInjection.getFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentFormTasksBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.includeToolbar.toolbar)
        setupFragment()
        observers()
    }

    private fun setupFragment() {
        binding.btnSave.setOnClickListener {
            viewModel.submitActions(
                FormTasksAction.ValidateInfoFormTask(
                    binding.edtDescription.text.toString(),
                    binding.rgStatus.checkedRadioButtonId
                )
            )
        }
    }

    private fun observers() {
        viewModel.state.observe(viewLifecycleOwner) { formState ->
            when {
                formState.success -> {
                    binding.progressBar.hide()
                    messageToast(formState.formTaskUIModel?.msgSuccess)
                }
                formState.isDescriptionError -> {
                    messageToast(formState.formTaskUIModel?.msgError)
                }
                formState.loading -> {
                    binding.progressBar.show()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}