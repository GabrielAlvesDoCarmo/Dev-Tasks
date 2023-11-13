package com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel

sealed interface LoginAction{
    data class ValidateForm(
        val email:String,
        val password :String
    ) : LoginAction

    data object IsUserLogged : LoginAction
}