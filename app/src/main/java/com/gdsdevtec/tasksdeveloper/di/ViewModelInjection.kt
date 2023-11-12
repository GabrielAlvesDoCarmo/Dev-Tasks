package com.gdsdevtec.tasksdeveloper.di

import com.gdsdevtec.tasksdeveloper.ui.FactoryViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCase
import com.gdsdevtec.tasksdeveloper.ui.form.usecase.FormTaskUseCase

data object ViewModelInjection {
    private val getRecoverUseCase : RecoverUseCase get() = RecoverUseCase()
    private val getLoginUseCase : LoginUseCase get() = LoginUseCase()
    private val getRegisterUseCase : RegisterUseCase get() = RegisterUseCase(RepositoryInjection.getRegisterRepository())
    private val getFormTaskUseCase : FormTaskUseCase get() = FormTaskUseCase()
    fun getFactory() = FactoryViewModel(
        loginUseCase = getLoginUseCase,
        recoverUseCase = getRecoverUseCase,
        registerUseCase = getRegisterUseCase,
        formTaskUseCase = getFormTaskUseCase
    )
}