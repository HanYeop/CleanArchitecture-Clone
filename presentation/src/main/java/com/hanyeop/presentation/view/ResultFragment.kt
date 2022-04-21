package com.hanyeop.presentation.view

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.FragmentResultBinding
import com.pss.presentation.base.BaseFragment

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {

    override fun init() {
        binding.fragment = this
    }

    fun View.clickBackMainBtn(){
        Log.d("tst5", "clickBackMainBtn: ")
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }
}