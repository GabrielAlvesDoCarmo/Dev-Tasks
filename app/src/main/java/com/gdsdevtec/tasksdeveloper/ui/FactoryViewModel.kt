package com.gdsdevtec.tasksdeveloper.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel.LoginViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel.RecoverViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.RegisterViewModel
import com.gdsdevtec.tasksdeveloper.ui.form.FormTasksViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.doing.DoingViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.done.DoneViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.todo.TodoViewModel

@Suppress("UNCHECKED_CAST")
class FactoryViewModel(
    private val loginUseCase: LoginUseCase,
    private val recoverUseCase: RecoverUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(loginUseCase) as T
            modelClass.isAssignableFrom(RecoverViewModel::class.java) -> RecoverViewModel(recoverUseCase) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel() as T
            modelClass.isAssignableFrom(FormTasksViewModel::class.java) -> FormTasksViewModel() as T
            modelClass.isAssignableFrom(DoingViewModel::class.java) -> DoingViewModel() as T
            modelClass.isAssignableFrom(DoneViewModel::class.java) -> DoneViewModel() as T
            modelClass.isAssignableFrom(TodoViewModel::class.java) -> TodoViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}