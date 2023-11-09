package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter

import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel

interface ButtonArrowAdapter {
    fun clickNext(taskModel: TaskModel)
    fun clickBack(taskModel: TaskModel)
}