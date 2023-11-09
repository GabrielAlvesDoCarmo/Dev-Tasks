package com.gdsdevtec.tasksdeveloper.ui.auth.register.model

import androidx.annotation.StringRes

data class RegisterUIModel(
    @StringRes val msgEmailError : Int? = null,
    @StringRes val msgPasswordError : Int? = null,
)
