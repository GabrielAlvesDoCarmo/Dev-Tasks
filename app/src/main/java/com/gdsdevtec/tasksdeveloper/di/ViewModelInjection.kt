package com.gdsdevtec.tasksdeveloper.di

import com.gdsdevtec.tasksdeveloper.ui.FactoryViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCase

object ViewModelInjection {




    fun getFactory() = FactoryViewModel(
        loginUseCase = getLoginUseCase(),
        recoverUseCase = getRecoverUseCase()
    )

    private fun getRecoverUseCase() = RecoverUseCase()

    private fun getLoginUseCase() = LoginUseCase()
}