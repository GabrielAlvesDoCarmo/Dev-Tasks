package com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase

sealed interface RegisterUseCaseState {
    sealed interface Form {
        data object EmailInvalid : Form
        data object EmailIsEmpty : Form
        data object PasswordInvalid : Form
        data object PasswordIsEmpty : Form
        data object SuccessRegisterForm :Form

    }
    sealed interface FirebaseRegister{
        data class Error(val msg : String) : FirebaseRegister
        data object Success : FirebaseRegister
    }

}