package com.gdsdevtec.tasksdeveloper.ui.auth.login.model

import androidx.annotation.StringRes

data class LoginUiModel(
    @StringRes val msgErrorEmail: Int? = null,
    @StringRes val msgErrorPassword: Int? = null,
)