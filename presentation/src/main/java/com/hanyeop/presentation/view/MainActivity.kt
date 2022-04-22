package com.hanyeop.presentation.view

import androidx.activity.viewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.ActivityMainBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import com.pss.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {

    }
}