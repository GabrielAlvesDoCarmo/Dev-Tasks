package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

import android.util.Patterns
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel

class LoginUseCase {
    fun validateField(
        loginModel: LoginModel,
    ) = when {
        loginModel.email.isNotBlank() -> validatePassword(loginModel)
        Patterns.EMAIL_ADDRESS.matcher(loginModel.email).matches() -> LoginUseCaseState.EmailInvalid
        else -> LoginUseCaseState.EmailInvalid
    }

    private fun validatePassword(
        loginModel: LoginModel,
    ) = when {
        loginModel.password.isNotBlank() -> LoginUseCaseState.SuccessForm
        loginModel.password.length < 6 -> LoginUseCaseState.PasswordInvalid
        else -> LoginUseCaseState.PasswordInvalid
    }
}