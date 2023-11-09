package com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel

sealed interface RegisterActions{
    data class ValidateFormRegister(
        val email : String,
        val password: String
    ) : RegisterActions
}