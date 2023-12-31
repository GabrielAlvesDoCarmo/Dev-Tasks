package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsdevtec.tasksdeveloper.databinding.ItemTaskBinding
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum.DOING
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum.DONE
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum.TODO
import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel
import com.gdsdevtec.tasksdeveloper.util.hide

class TaskAdapter(
    private val taskSelected: ButtonAdapterClick,
    private val navigateSelected : ButtonArrowAdapter
) : ListAdapter<TaskModel, TaskAdapter.TaskViewHolder>(diffUtil){
    inner class TaskViewHolder(
        val binding: ItemTaskBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
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
        binding: ItemTaskBinding,
    ) {
        binding.textDescription.text = task.description
        setDefaultButtons(binding, task)
        setIndicators(task, binding)
    }

    private fun setIndicators(task: TaskModel, binding: ItemTaskBinding) {
        when (task.status) {
            TODO -> {
                binding.ibActionBack.hide()
                binding.ibActionNext.setOnClickListener {
                    navigateSelected.clickNext(task)
                }
            }

            DONE -> {
                binding.ibActionNext.hide()
                binding.ibActionBack.setOnClickListener {
                    navigateSelected.clickBack(task)
                }
            }

            DOING -> {
                binding.ibActionBack.setOnClickListener {
                    navigateSelected.clickBack(task)
                }
                binding.ibActionNext.setOnClickListener {
                    navigateSelected.clickNext(task)
                }
            }
        }
    }

    private fun setDefaultButtons(binding: ItemTaskBinding, task: TaskModel) {
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