package com.gdsdevtec.tasksdeveloper.di

import com.gdsdevtec.tasksdeveloper.data.remote.repository.login.LoginRepository
import com.gdsdevtec.tasksdeveloper.data.remote.repository.register.RegisterRepository

data object RepositoryInjection {
    fun getRegisterRepository() = RegisterRepository.getInstance()
    fun getLoginRepository() = LoginRepository.getInstance()
}