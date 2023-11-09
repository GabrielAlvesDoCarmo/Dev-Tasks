package com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginUiModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCaseState

class LoginViewModel(
    private val useCase: LoginUseCase,
) : ViewModel() {
    private var _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> get() = _state

    fun submitAction(actions: LoginAction) {
        when (actions) {
            is LoginAction.ValidateForm -> validateForm(actions.email, actions.password)
        }
    }
    private fun validateForm(email: String, password: String) {
        when (useCase.validateField(LoginModel(email, password))) {
            LoginUseCaseState.EmailInvalid -> emailInvalid()
            LoginUseCaseState.PasswordInvalid -> passwordInvalid()
            LoginUseCaseState.SuccessForm -> successForm()
        }
    }
    private fun emailInvalid() {
        _state.value = getState().setError(
            LoginUiModel(
                msgErrorEmail = R.string.email_empty
            )
        )
    }
    private fun passwordInvalid() {
        _state.value = getState().setError(
            LoginUiModel(
                msgErrorPassword = R.string.password_empty
            )
        )
    }
    private fun successForm() {
        _state.value = getState().showSuccess()
    }
    private fun getState() = _state.value ?: LoginState()

}