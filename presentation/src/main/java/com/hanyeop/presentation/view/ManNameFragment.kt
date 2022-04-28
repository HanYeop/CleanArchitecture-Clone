package com.hanyeop.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.utils.ErrorType
import com.hanyeop.domain.utils.ScreenState
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentManNameBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import com.hanyeop.presentation.widget.utils.HOST
import com.hanyeop.presentation.widget.utils.KEY

class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun View.nextBtnClick(){
        binding.progressBar.visibility = View.VISIBLE
        mainViewModel.checkLoveCalculator(HOST, KEY,
            binding.editTxt.text.toString(),
            mainViewModel.womanNameResult)
    }

    private fun observeViewModel(){
        mainViewModel.apiCallEvent.observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            when (it) {
                ScreenState.LOADING -> this.findNavController()
                    .navigate(R.id.action_manNameFragment_to_resultFragment)
                ScreenState.ERROR -> toastErrorMsg()
                else -> shortShowToast("알수없는 오류가 발생했습니다")
            }
        }
    }

    private fun toastErrorMsg(){
        when(mainViewModel.apiErrorType){
            ErrorType.NETWORK ->longShowToast("네트워크 오류가 발생했습니다")
            ErrorType.SESSION_EXPIRED ->longShowToast("세션이 만료되었습니다")
            ErrorType.TIMEOUT ->longShowToast("서버 호출 시간이 초과되었습니다")
            ErrorType.UNKNOWN ->longShowToast("예기치 못한 오류가 발생했습니다")
        }
    }
}