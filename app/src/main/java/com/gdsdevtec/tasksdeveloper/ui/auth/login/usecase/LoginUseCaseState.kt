package com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase

sealed interface LoginUseCaseState{
    data object EmailInvalid : LoginUseCaseState
    data object EmailIsEmpty : LoginUseCaseState
    data object PasswordInvalid : LoginUseCaseState
    data object PasswordIsEmpty : LoginUseCaseState
    data object SuccessForm : LoginUseCaseState
}