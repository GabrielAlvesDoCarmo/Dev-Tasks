package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

import android.util.Patterns
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel

class LoginUseCase {
    fun validateField(
        loginModel: LoginModel,
    ) = when {
        loginModel.email.isBlank() -> LoginUseCaseState.EmailIsEmpty
        !Patterns.EMAIL_ADDRESS.matcher(loginModel.email).matches() -> LoginUseCaseState.EmailInvalid
        else -> validatePassword(loginModel)
    }

    private fun validatePassword(
        loginModel: LoginModel,
    ) = when {
        loginModel.password.isBlank() -> LoginUseCaseState.PasswordIsEmpty
        loginModel.password.length < 6 -> LoginUseCaseState.PasswordInvalid
        else -> LoginUseCaseState.SuccessForm
    }
}