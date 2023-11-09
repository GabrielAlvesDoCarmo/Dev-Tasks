package com.gdsdevtec.tasksdeveloper.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdevtec.tasksdeveloper.ui.auth.login.usecase.LoginUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.login.viewmodel.LoginViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.usecase.RecoverUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.recover.viewmodel.RecoverViewModel
import com.gdsdevtec.tasksdeveloper.ui.auth.register.usecase.RegisterUseCase
import com.gdsdevtec.tasksdeveloper.ui.auth.register.viewmodel.RegisterViewModel
import com.gdsdevtec.tasksdeveloper.ui.form.usecase.FormTaskUseCase
import com.gdsdevtec.tasksdeveloper.ui.form.viewmodel.FormTasksViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.doing.viewmodel.DoingViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.done.viewmodel.DoneViewModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.todo.viewmodel.TodoViewModel

@Suppress("UNCHECKED_CAST")
class FactoryViewModel(
    private val loginUseCase: LoginUseCase,
    private val recoverUseCase: RecoverUseCase,
    private val registerUseCase: RegisterUseCase,
    private val formTaskUseCase: FormTaskUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(loginUseCase) as T
            modelClass.isAssignableFrom(RecoverViewModel::class.java) -> RecoverViewModel(recoverUseCase) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(registerUseCase) as T
            modelClass.isAssignableFrom(FormTasksViewModel::class.java) -> FormTasksViewModel(formTaskUseCase) as T
            modelClass.isAssignableFrom(DoingViewModel::class.java) -> DoingViewModel() as T
            modelClass.isAssignableFrom(DoneViewModel::class.java) -> DoneViewModel() as T
            modelClass.isAssignableFrom(TodoViewModel::class.java) -> TodoViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}