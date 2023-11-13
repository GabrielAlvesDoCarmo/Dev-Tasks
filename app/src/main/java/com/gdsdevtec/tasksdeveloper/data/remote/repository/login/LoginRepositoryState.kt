package com.gdsdevtec.tasksdeveloper.data.remote.repository.login

sealed interface LoginRepositoryState {
    data object Success : LoginRepositoryState
    data class Error(val msg: String) : LoginRepositoryState
}