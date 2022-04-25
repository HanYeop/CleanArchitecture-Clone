package com.hanyeop.presentation.view

import android.util.Log
import androidx.activity.viewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.ActivitySplashBinding
import com.hanyeop.presentation.viewmodel.SplashViewModel
import com.hanyeop.presentation.widget.extension.startActivityWithFinish
import com.pss.barlibrary.CustomBar
import com.pss.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val appVersion = "1.0.0"

    override fun init() {
//        CustomBar.setTransparentBar(this)
        splashViewModel.checkAppVersion()
            .addOnSuccessListener {
                Log.d("로그","App version : ${it.value}")
                if(appVersion == it.value) this.startActivityWithFinish(this, MainActivity::class.java)
                else longShowToast("앱 버전이 다릅니다!")
            }
            .addOnFailureListener {
                shortShowToast("알수없는 오류가 발생했습니다, 오류코드 - ${it.message}")
            }
    }
}