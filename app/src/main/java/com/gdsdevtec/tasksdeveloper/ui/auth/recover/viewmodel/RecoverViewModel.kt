package com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.model.RecoverUIModel
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCaseState

class RecoverViewModel(
    private val useCase: RecoverUseCase,
) : ViewModel() {

    private var _state = MutableLiveData<RecoverState>()
    val state: LiveData<RecoverState> get() = _state

    fun submitActions(actions: RecoverAction) {
        when (actions) {
            is RecoverAction.ValidateEmailRecover -> validateRecoverEmail(actions.email)
        }
    }

    private fun validateRecoverEmail(email: String) {
        when (useCase.validateEmail(email)) {
            is RecoverUseCaseState.EmailRecoverInvalid -> successRecover()
            is RecoverUseCaseState.EmailRecoverValid -> emailInvalid(R.string.invalid_email_register_fragment)
            is RecoverUseCaseState.EmailRecoverEmpty -> emailInvalid(R.string.email_empty)
        }
    }

    private fun successRecover() {
        _state.value = getState().showSuccess(
            RecoverUIModel(msgSuccess = R.string.recover_email_success)
        )
    }

    private fun emailInvalid(@StringRes msg: Int) {
        _state.value = getState().showErrorMsg(
            RecoverUIModel(msgError = msg)
        )
    }

    private fun getState() = _state.value ?: RecoverState()
}