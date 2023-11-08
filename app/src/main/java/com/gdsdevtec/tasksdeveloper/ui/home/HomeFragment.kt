package com.gdsdevtec.tasksdeveloper.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentHomeBinding
import com.gdsdevtec.tasksdeveloper.ui.home.doing.DoingFragment
import com.gdsdevtec.tasksdeveloper.ui.home.done.DoneFragment
import com.gdsdevtec.tasksdeveloper.ui.home.todo.TodoFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val pageAdapter by lazy {
        HomeViewPagerAdapter(requireActivity()).apply {
            addFragment(TodoFragment(), R.string.status_task_todo)
            addFragment(DoingFragment(), R.string.status_task_doing)
            addFragment(DoneFragment(), R.string.status_task_done)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {
        binding.viewPager.apply {
            adapter = pageAdapter
            offscreenPageLimit = pageAdapter.itemCount
        }
        binding.btnLogout.setOnClickListener { }
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
