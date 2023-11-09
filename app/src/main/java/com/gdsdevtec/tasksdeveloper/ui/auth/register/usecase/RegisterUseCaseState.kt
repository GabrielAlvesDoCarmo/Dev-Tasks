package com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase

sealed interface RegisterUseCaseState {

    data object EmailInvalid : RegisterUseCaseState
    data object EmailIsEmpty : RegisterUseCaseState
    data object PasswordInvalid : RegisterUseCaseState
    data object PasswordIsEmpty : RegisterUseCaseState
    data object SuccessRegisterForm :RegisterUseCaseState
}