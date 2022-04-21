package com.hanyeop.presentation.view

import android.view.View
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentWomanNameBinding
import com.pss.presentation.base.BaseFragment

class WomanNameFragment : BaseFragment<FragmentWomanNameBinding>(R.layout.fragment_woman_name) {

    override fun init() {
        binding.fragment = this
    }

    fun View.nextBtnClick(){
        this.findNavController().navigate(R.id.action_womanNameFragment_to_manNameFragment)
    }
}