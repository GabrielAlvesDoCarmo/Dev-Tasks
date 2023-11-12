package com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterUIModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCaseState
import com.gdsdevtec.tasksdeveloper.util.cryptPassword
import com.gdsdevtec.tasksdeveloper.util.generateIdUser
import com.gdsdevtec.tasksdeveloper.util.transformUUID
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: RegisterUseCase,
) : ViewModel() {
    private var _state = MutableLiveData<RegisterState>()
    val state: LiveData<RegisterState> get() = _state
    fun submitActions(actions: RegisterActions) {
        when (actions) {
            is RegisterActions.ValidateFormRegister -> validateRegisterForm(
                actions.email,
                actions.password
            )
        }
    }

    private fun validateRegisterForm(email: String, password: String) {
        val result = useCase.validateRegisterForm(
            email, password
        )
        when (result) {
            is RegisterUseCaseState.Form.EmailInvalid -> emailError(R.string.invalid_email_register_fragment)
            is RegisterUseCaseState.Form.EmailIsEmpty -> emailError(R.string.email_empty)
            is RegisterUseCaseState.Form.PasswordInvalid -> passwordError(R.string.strong_password_register_fragment)
            is RegisterUseCaseState.Form.PasswordIsEmpty -> passwordError(R.string.password_empty_register_fragment)
            is RegisterUseCaseState.Form.SuccessRegisterForm -> registerInFirebase(email, password)

        }
    }

    private fun registerInFirebase(email: String, password: String) {
        _state.value = getState().showLoading()
        viewModelScope.launch {
            when (val result = useCase.registerUserFirebase(registerModel(email, password))) {
                is RegisterUseCaseState.FirebaseRegister.Error -> setErrorFirebase(result.msg)
                is RegisterUseCaseState.FirebaseRegister.Success -> successValidateForm()
            }
        }
    }

    private fun setErrorFirebase(msg: String) {
        _state.value = getState().setError(
            RegisterUIModel(msgFirebaseRegister = msg)
        )
    }

    private fun registerModel(
        email: String,
        password: String,
    ) = RegisterModel(
        uuid = email.generateIdUser(),
        email = email,
        password = password.transformUUID().cryptPassword()
    )

    private fun successValidateForm() {
        _state.value = getState().showSuccess()
    }

    private fun passwordError(msg: Int) {
        _state.value = getState().setError(
            RegisterUIModel(msgPasswordError = msg)
        )
    }

    private fun emailError(msg: Int) {
        _state.value = getState().setError(
            RegisterUIModel(msgEmailError = msg)
        )
    }

    private fun getState() = _state.value ?: RegisterState()
}