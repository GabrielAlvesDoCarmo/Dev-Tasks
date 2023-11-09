package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsdevtec.tasksdeveloper.databinding.ItemTaskImportantBinding
import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel

class TaskImportantAdapter(
    private val taskSelected: ButtonAdapterClick,
) : ListAdapter<TaskModel, TaskImportantAdapter.TaskViewHolder>(diffUtil) {
    inner class TaskViewHolder(
        val binding: ItemTaskImportantBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskImportantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        bindingLayout(task, holder.binding)
    }

    private fun bindingLayout(
        task: TaskModel,
        binding: ItemTaskImportantBinding,
    ) {
        binding.textDescription.text = task.description
        setDefaultButtons(binding, task)
    }

    private fun setDefaultButtons(binding: ItemTaskImportantBinding, task: TaskModel) {
        binding.btnDelete.setOnClickListener { taskSelected.clickRemove(task) }
        binding.btnEdit.setOnClickListener { taskSelected.clickEdit(task) }
        binding.btnDetails.setOnClickListener { taskSelected.clickDetails(task) }
    }

    private companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<TaskModel>() {
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return oldItem == newItem &&
                        oldItem.description == newItem.description
            }

        }
    }
}