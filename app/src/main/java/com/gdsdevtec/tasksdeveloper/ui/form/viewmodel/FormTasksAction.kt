package com.gdsdevtec.tasksdeveloper.ui.form.viewmodel
sealed interface FormTasksAction{
    data class ValidateInfoFormTask(
        val description : String,
        val radioButtonIdRes: Int
    ) : FormTasksAction
}