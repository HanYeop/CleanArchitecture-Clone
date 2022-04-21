package com.hanyeop.presentation.view

import android.view.View
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentMainBinding
import com.pss.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun init() {
        binding.fragment = this
    }

    fun View.startBtnClick(){
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }
}