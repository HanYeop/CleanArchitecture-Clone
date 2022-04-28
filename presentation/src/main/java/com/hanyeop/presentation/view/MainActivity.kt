package com.hanyeop.presentation.view

import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityMainBinding
import com.pss.barlibrary.CustomBar.Companion.setTransparentBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        setTransparentBar(this)
    }
}