package com.gdsdevtec.tasksdeveloper.ui.form.viewmodel

import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskUIModel

data class FormTasksState(
    val success: Boolean = false,
    val formTaskUIModel: FormTaskUIModel? = null,
    val loading: Boolean = false,
) {

    val isDescriptionError: Boolean get() = !success && !loading && formTaskUIModel?.msgError != null
    fun setError(formTaskUIModel: FormTaskUIModel) = copy(
        loading = false,
        success = false,
        formTaskUIModel = formTaskUIModel
    )

    fun showSuccess(formTaskUIModel: FormTaskUIModel) = copy(
        loading = false,
        success = true,
        formTaskUIModel = formTaskUIModel
    )

    fun showLoading() = copy(
        loading = true
    )
}