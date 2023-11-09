package com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterUIModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCaseState

class RegisterViewModel(
    private val useCase: RegisterUseCase,
) : ViewModel() {
    private var _state = MutableLiveData<RegisterState>()
    val state: LiveData<RegisterState> get() = _state


    fun submitActions(actions: RegisterActions) {
        when (actions) {
            is RegisterActions.ValidateFormRegister -> validateRegisterForm(
                actions.email,
                actions.password
            )
        }
    }

    private fun validateRegisterForm(email: String, password: String) {
        val result = useCase.validateRegisterForm(
            RegisterModel(email, password)
        )
        when (result) {
            is RegisterUseCaseState.EmailInvalid -> emailError(R.string.invalid_email_register_fragment)
            is RegisterUseCaseState.EmailIsEmpty -> emailError(R.string.email_empty)
            is RegisterUseCaseState.PasswordInvalid -> passwordError(R.string.strong_password_register_fragment)
            is RegisterUseCaseState.PasswordIsEmpty -> passwordError(R.string.password_empty_register_fragment)
            is RegisterUseCaseState.SuccessRegisterForm -> successValidateForm()
        }
    }

    private fun successValidateForm() {
        _state.value = getState().showSuccess()
    }

    private fun passwordError(msg: Int) {
        _state.value = getState().setError(
            RegisterUIModel(msgPasswordError = msg)
        )
    }

    private fun emailError(msg: Int) {
        _state.value = getState().setError(
            RegisterUIModel(msgEmailError = msg)
        )
    }

    private fun getState() = _state.value ?: RegisterState()
}