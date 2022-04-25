package com.hanyeop.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentWomanNameBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import com.pss.presentation.base.BaseFragment

class WomanNameFragment : BaseFragment<FragmentWomanNameBinding>(R.layout.fragment_woman_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun init() {
        binding.fragment = this
    }

    fun View.nextBtnClick(){
        mainViewModel.womanNameResult = binding.editTxt.toString()
        this.findNavController().navigate(R.id.action_womanNameFragment_to_manNameFragment)
    }
}