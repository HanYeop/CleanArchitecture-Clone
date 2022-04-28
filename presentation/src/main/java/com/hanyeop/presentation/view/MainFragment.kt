package com.hanyeop.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.adapter.ScoreRecyclerViewAdapter
import com.hanyeop.presentation.databinding.FragmentMainBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import com.hanyeop.presentation.widget.extension.showVertical
import com.pss.library.CountNumberEvent
import com.pss.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun init() {
        binding.fragment = this
        mainViewModel.getStatisticsDisplay()
        mainViewModel.getScore()
        if (mainViewModel.getStatisticsDisplayEvent.value != null)
            CountNumberEvent.countNumber(
                0,
                mainViewModel.getStatisticsDisplayEvent.value!!,
                binding.number,
                1000
            )
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

        mainViewModel.getScoreEvent.observe(this){
            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        binding.scoreRecyclerView.adapter = ScoreRecyclerViewAdapter(mainViewModel)
        binding.scoreRecyclerView.showVertical(requireContext())
    }
}