package com.gdsdevtec.tasksdeveloper.ui.home.viewpager.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentTodoBinding
import com.gdsdevtec.tasksdeveloper.ui.home.container.model.TaskModel
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.ButtonAdapterClick
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.ButtonArrowAdapter
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.TaskAdapter
import com.gdsdevtec.tasksdeveloper.ui.home.viewpager.adapter.TaskImportantAdapter
import com.gdsdevtec.tasksdeveloper.util.nextFragment

class TodoFragment : Fragment(),ButtonAdapterClick,ButtonArrowAdapter {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskImpAdapter: TaskImportantAdapter

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
        initRecyclerView()
        getTasks()
    }

    private fun initRecyclerView() = binding.apply {
        taskAdapter = TaskAdapter(this@TodoFragment,this@TodoFragment)
        taskImpAdapter = TaskImportantAdapter(this@TodoFragment)
        val concatAdapter = ConcatAdapter(
            taskImpAdapter,taskAdapter
        )
        rvTasks.apply {
            setHasFixedSize(true)
            adapter = concatAdapter
        }
    }
    private fun getTasks(){
        val listTask =
            listOf(
                TaskModel(1,"descricao 1 "),
                TaskModel(1,"descricao 2"),
                TaskModel(1,"descricao 2"),
                TaskModel(1,"descricao 2"),
            )

        val listImportantTask =
            listOf(
                TaskModel(1,"descricao 10 "),
                TaskModel(12,"descricao 20"),
            )
        taskAdapter.submitList(listTask)
        taskImpAdapter.submitList(listImportantTask)
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