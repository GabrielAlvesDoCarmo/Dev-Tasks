package com.gdsdevtec.tasksdeveloper.ui.form.usecase

sealed interface FormTaskUseCaseState {
    data object DescriptionErrorEmpty : FormTaskUseCaseState
    data object DescriptionErrorVeryShort : FormTaskUseCaseState
    data object SuccessDescription : FormTaskUseCaseState
}