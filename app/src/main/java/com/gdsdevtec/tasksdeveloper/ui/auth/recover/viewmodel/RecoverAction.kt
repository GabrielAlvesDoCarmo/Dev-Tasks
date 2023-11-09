package com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel

sealed interface RecoverAction{
    data class ValidateEmailRecover(val email: String) : RecoverAction
}