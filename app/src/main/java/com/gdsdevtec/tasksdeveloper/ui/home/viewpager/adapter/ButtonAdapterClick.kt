package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter

import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel

interface ButtonAdapterClick {
    fun clickNext(taskModel: TaskModel)
    fun clickBack(taskModel: TaskModel)
    fun clickRemove(taskModel: TaskModel)
    fun clickEdit(taskModel: TaskModel)
    fun clickDetails(taskModel: TaskModel)
}