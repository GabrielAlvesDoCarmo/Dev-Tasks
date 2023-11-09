package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentTodoBinding
import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.ButtonAdapterClick
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.TaskAdapter
import com.gdsdevtec.tasksdeveloper.util.nextFragment

class TodoFragment : Fragment(),ButtonAdapterClick {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentTodoBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    private fun setupFragment() {
        listeners()
        initRecyclerView(
            listOf(
                TaskModel(1,"descricao 1 "),
                TaskModel(1,"descricao 2"),
                TaskModel(1,"descricao 2"),
                TaskModel(1,"descricao 2"),
            )
        )
    }

    private fun initRecyclerView(taskList: List<TaskModel>) = binding.apply {
        taskAdapter = TaskAdapter(taskList,this@TodoFragment)
        rvTasks.apply {
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun listeners() = binding.apply {
        fabAdd.setOnClickListener {
            nextFragment(R.id.action_homeFragment_to_formTasksFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickNext(taskModel: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun clickBack(taskModel: TaskModel) {}
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