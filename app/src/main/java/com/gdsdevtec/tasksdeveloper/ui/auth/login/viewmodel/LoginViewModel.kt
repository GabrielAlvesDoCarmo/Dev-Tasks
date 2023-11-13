package com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginUiModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCaseState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: LoginUseCase,
) : ViewModel() {
    private var _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> get() = _state


    fun submitAction(actions: LoginAction) {
        when (actions) {
            is LoginAction.ValidateForm -> validateForm(actions.email, actions.password)
            is LoginAction.IsUserLogged -> verifyUserLogged(useCase.isUserLogged)
        }
    }

    private fun verifyUserLogged(userLogged: Boolean) {
        _state.value = getState().setUserLogged(
            LoginUiModel(
                isUserLogged = userLogged
            )
        )
    }

    private fun validateForm(email: String, password: String) {
        val loginModel = LoginModel(email, password)
        when (useCase.validateField(loginModel)) {
            LoginUseCaseState.Form.EmailInvalid -> emailInvalid(R.string.invalid_email_register_fragment)
            LoginUseCaseState.Form.EmailIsEmpty -> emailInvalid(R.string.email_empty)
            LoginUseCaseState.Form.PasswordInvalid -> passwordInvalid(R.string.password_invalid)
            LoginUseCaseState.Form.PasswordIsEmpty -> passwordInvalid(R.string.password_empty)
            LoginUseCaseState.Form.SuccessForm -> firebaseLogin(loginModel)
        }
    }

    private fun firebaseLogin(loginModel: LoginModel) {
        _state.value = getState().showLoading()
        viewModelScope.launch {
            when (val resultLogin = useCase.loginUser(loginModel)) {
                is LoginUseCaseState.FirebaseLogin.Error -> setFirebaseMsgError(resultLogin)
                is LoginUseCaseState.FirebaseLogin.Success -> successForm()
            }
        }
    }

    private fun setFirebaseMsgError(resultLogin: LoginUseCaseState.FirebaseLogin.Error) {
       _state.value =  getState().setError(
            LoginUiModel(
                msgErrorFirebase = resultLogin.msg
            )
        )
    }

    private fun emailInvalid(msg: Int) {
        _state.value = getState().setError(
            LoginUiModel(msgErrorEmail = msg)
        )
    }

    private fun passwordInvalid(msg: Int) {
        _state.value = getState().setError(
            LoginUiModel(msgErrorPassword = msg)
        )
    }

    private fun successForm() {
        _state.value = getState().showSuccess()
    }

    private fun getState() = _state.value ?: LoginState()

}