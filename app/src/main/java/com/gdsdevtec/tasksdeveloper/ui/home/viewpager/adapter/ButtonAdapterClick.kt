package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter

import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel

interface ButtonAdapterClick {
    fun clickRemove(taskModel: TaskModel)
    fun clickEdit(taskModel: TaskModel)
    fun clickDetails(taskModel: TaskModel)
}