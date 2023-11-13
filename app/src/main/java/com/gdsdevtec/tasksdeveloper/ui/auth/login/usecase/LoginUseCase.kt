package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

import android.util.Patterns
import com.gdsdevtec.tasksdeveloper.data.remote.repository.login.LoginRepository
import com.gdsdevtec.tasksdeveloper.data.remote.repository.login.LoginRepositoryState
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel
import com.gdsdevtec.tasksdeveloper.util.toLoginRequest

class LoginUseCase(
    private val repository: LoginRepository,
) {

    val isUserLogged: Boolean get() = repository.isUserLogged
    fun validateField(
        loginModel: LoginModel,
    ) = when {
        loginModel.email.isBlank() -> LoginUseCaseState.Form.EmailIsEmpty
        !Patterns.EMAIL_ADDRESS.matcher(loginModel.email)
            .matches() -> LoginUseCaseState.Form.EmailInvalid

        else -> validatePassword(loginModel)
    }

    private fun validatePassword(
        loginModel: LoginModel,
    ) = when {
        loginModel.password.isBlank() -> LoginUseCaseState.Form.PasswordIsEmpty
        loginModel.password.length < 6 -> LoginUseCaseState.Form.PasswordInvalid
        else -> LoginUseCaseState.Form.SuccessForm
    }

    suspend fun loginUser(loginModel: LoginModel) = repository.login(
        loginModel.toLoginRequest()
    ).let {
        return@let when (it) {
            is LoginRepositoryState.Error -> LoginUseCaseState.FirebaseLogin.Error(it.msg)
            is LoginRepositoryState.Success -> LoginUseCaseState.FirebaseLogin.Success
        }
    }
}