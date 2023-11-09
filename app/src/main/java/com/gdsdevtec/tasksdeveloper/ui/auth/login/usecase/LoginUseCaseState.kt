package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

sealed interface LoginUseCaseState{
    data object EmailInvalid : LoginUseCaseState
    data object PasswordInvalid : LoginUseCaseState
    data object SuccessForm : LoginUseCaseState
}