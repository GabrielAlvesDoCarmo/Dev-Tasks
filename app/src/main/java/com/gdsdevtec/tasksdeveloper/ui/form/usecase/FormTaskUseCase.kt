package com.gdsdevtec.tasksdeveloper.ui.form.usecase

import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTasksModel

class FormTaskUseCase {

    fun validateInfoFormTasks(
        formTasksModel: FormTasksModel
    ) =when{
        formTasksModel.description.isBlank()-> FormTaskUseCaseState.DescriptionErrorEmpty
        formTasksModel.description.length < 6-> FormTaskUseCaseState.DescriptionErrorVeryShort
        else -> FormTaskUseCaseState.SuccessDescription
    }
}