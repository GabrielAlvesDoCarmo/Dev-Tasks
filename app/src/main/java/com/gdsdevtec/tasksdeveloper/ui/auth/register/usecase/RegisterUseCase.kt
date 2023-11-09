package com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase

import android.util.Patterns
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel

class RegisterUseCase {

    fun validateRegisterForm(
        registerModel: RegisterModel,
    ) = when {
        registerModel.email.isBlank() -> RegisterUseCaseState.EmailIsEmpty
        !Patterns.EMAIL_ADDRESS.matcher(registerModel.email).matches() -> RegisterUseCaseState.EmailInvalid
        else -> validatePasswordRegisterForm(registerModel.password)
    }

    private fun validatePasswordRegisterForm(
        password: String
    ) = when{
        password.isBlank()-> RegisterUseCaseState.PasswordIsEmpty
        password.length < 6 -> RegisterUseCaseState.PasswordInvalid
        else -> RegisterUseCaseState.SuccessRegisterForm
    }
}