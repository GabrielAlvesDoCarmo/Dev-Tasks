package com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase

import android.util.Patterns.EMAIL_ADDRESS

class RecoverUseCase {
    fun validateEmail(email: String): RecoverUseCaseState {
        return when {
            email.isBlank() || email.isEmpty() -> RecoverUseCaseState.EmailRecoverEmpty
            EMAIL_ADDRESS.matcher(email).matches() -> RecoverUseCaseState.EmailRecoverInvalid
            else -> RecoverUseCaseState.EmailRecoverValid
        }
    }
}