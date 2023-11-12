package com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel

import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterUIModel

data class RegisterState(
    val success: Boolean = false,
    val registerUIModel: RegisterUIModel? = null,
    val loading: Boolean = false,
) {

    val isMsgEmailError: Boolean get() = !success && !loading && registerUIModel?.msgEmailError != null
    val isMsgPasswordError: Boolean get() = !success && !loading && registerUIModel?.msgPasswordError != null
    val isMsgFirebaseError: Boolean get() = !success && !loading && registerUIModel?.msgFirebaseRegister != null
    fun setError(
        registerUIModel: RegisterUIModel,
    ) = copy(
        registerUIModel = registerUIModel
    )

    fun showSuccess() = copy(
        success = true
    )

    fun showLoading() = copy(
        loading = true
    )
}