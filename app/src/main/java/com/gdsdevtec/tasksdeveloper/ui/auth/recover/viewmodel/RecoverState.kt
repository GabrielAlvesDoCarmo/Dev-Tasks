package com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel

import com.gdsdevtec.tasksdeveloper.ui.auth.recover.model.RecoverUIModel

data class RecoverState(
    val success: Boolean = false,
    val recoverUIModel: RecoverUIModel? = null,
    val loading: Boolean = false,
) {

    val isEmailError: Boolean get() = !success && !loading && recoverUIModel?.msgError != null
    fun showSuccess(recoverUIModel: RecoverUIModel) = copy(
        loading = false,
        success = true,
        recoverUIModel = recoverUIModel
    )

    fun showErrorMsg(recoverUIModel: RecoverUIModel) = copy(
        success = false,
        loading = false,
        recoverUIModel = recoverUIModel
    )

    fun showLoading() = copy(
        loading = true
    )

}
