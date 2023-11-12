package com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase

import android.util.Patterns
import com.gdsdevtec.tasksdeveloper.data.remote.repository.register.RegisterRepository
import com.gdsdevtec.tasksdeveloper.data.remote.repository.register.RegisterRepositoryState
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCaseState.FirebaseRegister
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCaseState.Form
import com.gdsdevtec.tasksdeveloper.util.toRegisterRequest

class RegisterUseCase(
    private val repository: RegisterRepository,
) {

    fun validateRegisterForm(
        email: String,
        password: String,
    ) = when {
        email.isBlank() -> Form.EmailIsEmpty
        !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() -> Form.EmailInvalid

        else -> validatePasswordRegisterForm(password)
    }

    private fun validatePasswordRegisterForm(
        password: String,
    ) = when {
        password.isBlank() -> Form.PasswordIsEmpty
        password.length < 6 -> Form.PasswordInvalid
        else -> Form.SuccessRegisterForm
    }

    suspend fun registerUserFirebase(
        user: RegisterModel,
    ) = repository.registerUser(
        user.toRegisterRequest()
    ).let {
        return@let when(it){
            is RegisterRepositoryState.ErrorRegister -> FirebaseRegister.Error(
                it.msgError
            )
            is RegisterRepositoryState.SuccessRegister -> FirebaseRegister.Success
        }
    }
}
