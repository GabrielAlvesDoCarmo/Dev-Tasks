package com.gdsdevtec.tasksdeveloper.util

import com.gdsdevtec.tasksdeveloper.data.remote.repository.login.LoginUserRequest
import com.gdsdevtec.tasksdeveloper.data.remote.repository.register.RegisterUserRequest
import com.gdsdevtec.tasksdeveloper.ui.auth.login.model.LoginModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel

fun RegisterModel.toRegisterRequest(): RegisterUserRequest {
    return RegisterUserRequest(
        id = this.uuid,
        email = this.email,
        password = this.password
    )
}

fun LoginModel.toLoginRequest(): LoginUserRequest {
    return LoginUserRequest(
        email = this.email, password = this.password
    )
}