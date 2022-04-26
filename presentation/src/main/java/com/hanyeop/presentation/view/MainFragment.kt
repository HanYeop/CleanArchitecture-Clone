package com.hanyeop.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentMainBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import com.pss.library.CountNumberEvent
import com.pss.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun init() {
        binding.fragment = this
        mainViewModel.getStatisticsDisplay()
        observeViewModel()
    }

    fun View.startBtnClick(){
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }

    private fun observeViewModel() {
        mainViewModel.getStatisticsDisplayEvent.observe(this) {
            binding.number.text = ""
            CountNumberEvent.countNumber(0, it, binding.number, 1000)
        }
    }
}