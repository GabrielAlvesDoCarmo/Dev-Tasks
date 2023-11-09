package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.doing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.databinding.FragmentDoingBinding
import com.gdsdevtec.tasksdeveloper.ui.form.model.FormTaskStateEnum
import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.ButtonAdapterClick
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.ButtonArrowAdapter
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.TaskAdapter

class DoingFragment : Fragment(),ButtonAdapterClick, ButtonArrowAdapter {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter : TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentDoingBinding.inflate(
        inflater,container,false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(
            listOf(TaskModel(2,"outra description", status = FormTaskStateEnum.DOING))
        )
    }

    private fun initRecyclerView(taskList: List<TaskModel>) = binding.apply {
        taskAdapter = TaskAdapter(this@DoingFragment,this@DoingFragment)
        taskAdapter.submitList(taskList)
        rvTasks.apply {
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickNext(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun clickBack(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun clickRemove(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun clickEdit(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun clickDetails(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }
}