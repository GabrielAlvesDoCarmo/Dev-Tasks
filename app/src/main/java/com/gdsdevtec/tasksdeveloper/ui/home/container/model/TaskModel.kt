package com.gdsdevtec.tasksdeveloper.ui.home.container.model

import android.os.Parcelable
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum
import com.gdsdevtec.tasksdeveloper.ui.form.viewmodel.FormTasksState
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModel(
    val id : Long = 0,
    val description : String,
    val status : FormTaskStateEnum = FormTaskStateEnum.TODO
) : Parcelable