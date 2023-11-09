package com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase

sealed interface RecoverUseCaseState{
    data object EmailRecoverValid : RecoverUseCaseState
    data object EmailRecoverInvalid : RecoverUseCaseState
    data object EmailRecoverEmpty : RecoverUseCaseState
}