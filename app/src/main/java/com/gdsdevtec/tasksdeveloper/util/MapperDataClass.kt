package com.gdsdevtec.tasksdeveloper.util

import com.gdsdevtec.tasksdeveloper.data.remote.repository.register.RegisterUserRequest
import com.gdsdevtec.tasksdeveloper.ui.auth.register.model.RegisterModel

fun RegisterModel.toRegisterRequest(): RegisterUserRequest {
    return RegisterUserRequest(
        id = this.uuid,
        email = this.email,
        password = this.password
    )
}