package com.gdsdevtec.tasksdeveloper.ui.home.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.FragmentHomeBinding
import com.gdsdevtec.tasksdeveloper.ui.home.doing.view.DoingFragment
import com.gdsdevtec.tasksdeveloper.ui.home.done.view.DoneFragment
import com.gdsdevtec.tasksdeveloper.ui.home.todo.view.TodoFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
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
        val pageAdapter = setupPageAdapter()
        binding.viewPager.apply {
            adapter = pageAdapter
            offscreenPageLimit = pageAdapter.itemCount
        }
        binding.btnLogout.setOnClickListener {
            activity?.finish()
        }
        attachPageAdapter(pageAdapter)
    }

    private fun attachPageAdapter(pageAdapter: HomeViewPagerAdapter) {
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()
    }

    private fun setupPageAdapter() = HomeViewPagerAdapter(
        requireActivity()
    ).apply {
        addFragment(TodoFragment(), R.string.status_task_todo)
        addFragment(DoingFragment(), R.string.status_task_doing)
        addFragment(DoneFragment(), R.string.status_task_done)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

