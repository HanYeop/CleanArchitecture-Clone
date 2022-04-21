package com.hanyeop.presentation.view

import android.view.View
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentManNameBinding
import com.pss.presentation.base.BaseFragment

class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {

    override fun init() {
        binding.fragment = this
    }

    fun View.nextBtnClick(){
        this.findNavController().navigate(R.id.action_manNameFragment_to_resultFragment)
    }
}