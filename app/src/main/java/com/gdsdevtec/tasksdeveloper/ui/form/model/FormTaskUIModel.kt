package com.gdsdevtec.tasksdeveloper.ui.form.model

import androidx.annotation.StringRes

data class FormTaskUIModel(
    @StringRes val msgError : Int? = null,
    @StringRes val msgSuccess : Int? = null
)