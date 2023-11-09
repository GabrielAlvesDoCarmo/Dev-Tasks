package com.gdsdevtec.tasksdeveloper.ui.form.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskUIModel
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTasksModel
import com.gdsdevtec.tasksdeveloper.ui.form.usecase.FormTaskUseCase
import com.gdsdevtec.tasksdeveloper.ui.form.usecase.FormTaskUseCaseState

class FormTasksViewModel(
    private val useCase: FormTaskUseCase,
) : ViewModel() {

    private var _state = MutableLiveData<FormTasksState>()
    val state: LiveData<FormTasksState> get() = _state
    private val getState: FormTasksState get() = _state.value ?: FormTasksState()
    fun submitActions(actions: FormTasksAction) {
        when (actions) {
            is FormTasksAction.ValidateInfoFormTask -> validateFormTask(
                actions.description,
                actions.radioButtonIdRes
            )
        }
    }

    private fun validateFormTask(description: String, radioButtonIdRes: Int) {
        val state = getTaskState(radioButtonIdRes)
        val result = useCase.validateInfoFormTasks(
            FormTasksModel(description, state)
        )
        when (result) {
            is FormTaskUseCaseState.DescriptionErrorEmpty -> setDescriptionError(R.string.description_empty_form_task_fragment)
            is FormTaskUseCaseState.DescriptionErrorVeryShort -> setDescriptionError(R.string.description_short_form_task_fragment)
            is FormTaskUseCaseState.SuccessDescription -> showSuccess()
        }
    }

    private fun showSuccess() {
        _state.value = getState.showSuccess(
            FormTaskUIModel(
                msgSuccess = R.string.text_save_sucess_form_task_fragment
            )
        )
    }

    private fun setDescriptionError(msg: Int) {
        _state.value = getState.setError(
            FormTaskUIModel(msgError = msg)
        )
    }

    private fun getTaskState(radioButtonIdRes: Int) = when (radioButtonIdRes) {
        R.id.rbDone -> FormTaskStateEnum.DONE
        R.id.rbTodo -> FormTaskStateEnum.TODO
        R.id.rbDoing -> FormTaskStateEnum.DOING
        else -> FormTaskStateEnum.TODO
    }

}