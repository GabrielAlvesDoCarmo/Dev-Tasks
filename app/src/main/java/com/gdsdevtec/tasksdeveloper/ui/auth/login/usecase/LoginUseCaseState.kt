package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

sealed interface LoginUseCaseState{

    sealed interface Form{
        data object EmailInvalid : Form
        data object EmailIsEmpty : Form
        data object PasswordInvalid : Form
        data object PasswordIsEmpty : Form
        data object SuccessForm : Form
    }
   sealed interface FirebaseLogin{
       data object Success : FirebaseLogin
       data class Error(val msg : String) : FirebaseLogin
   }
}