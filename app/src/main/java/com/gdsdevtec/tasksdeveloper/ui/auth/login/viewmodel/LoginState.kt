package com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel

import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginUiModel

data class LoginState(
    val success: Boolean = false,
    val loginUiModel: LoginUiModel? = null,
    val loading: Boolean = false,
) {
    val isEmailError: Boolean get() = !success && loginUiModel?.msgErrorEmail != null
    val isPasswordError: Boolean get() = !success && loginUiModel?.msgErrorPassword != null

    fun setError(loginUiModel: LoginUiModel) = copy(
        success = false,
        loginUiModel = loginUiModel,
        loading = false
    )
    fun showLoading() = copy(
        loading = true
    )

    fun showSuccess() = copy(
        success = true
    )
}