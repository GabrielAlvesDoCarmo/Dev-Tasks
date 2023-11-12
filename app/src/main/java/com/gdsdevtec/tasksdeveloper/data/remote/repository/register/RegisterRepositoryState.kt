package com.gdsdevtec.tasksdeveloper.data.remote.repository.register

sealed interface RegisterRepositoryState {
    data object SuccessRegister : RegisterRepositoryState
    data class ErrorRegister(
        val msgError : String
    ) : RegisterRepositoryState
}