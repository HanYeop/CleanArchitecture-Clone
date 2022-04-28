package com.hanyeop.presentation.view

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentResultBinding
import com.hanyeop.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

const val TAG = "ResultFragment"
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun init() {
        binding.fragment = this
        initResult()
        saveScore()
    }

    private fun saveScore() = with(mainViewModel.apiCallResult){
        mainViewModel.setScore(this.sname, this.fname, this.percentage, nowTime())
    }

    private fun nowTime(): String = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale("ko", "KR")).format(
        Date(System.currentTimeMillis())
    )

    private fun initResult() {
        binding.percentage.text = mainViewModel.apiCallResult.percentage.toString()
//        saveStatistics() // test
        when (mainViewModel.apiCallResult.percentage) {
            in 0..20 -> setLoveMsgTxt("조금 힘들어보여요")
            in 21..40 -> setLoveMsgTxt("노력해 보세요")
            in 41..70 -> setLoveMsgTxt("기대해도 좋겠는데요?")
            in 71..90 -> setLoveMsgTxt("일단 축하드려요")
            in 91..99 -> setLoveMsgTxt("와우.. 눈을 의심하고 있어요")
            100 -> {
                saveStatistics()
                setLoveMsgTxt("완벽하네요, 축하드려요")
            }
            else -> setLoveMsgTxt("알수없는 힘!!")
        }
        binding.backMainBtn.isEnabled = true
    }

    private fun saveStatistics() {
        mainViewModel.getStatistics()
            .addOnSuccessListener {
                Log.d(TAG, "saveStatistics: $it")

                // db에 이미 존재
                if (it.value != null) {
                    mainViewModel.setStatistics(it.value.toString().toInt() + 1)
                        .addOnFailureListener {
                            error()
                        }
                }
                // db에 존재 X (최초 생성)
                else{
                    mainViewModel.setStatistics(1)
                        .addOnFailureListener {
                            error()
                        }
                }
            }
            .addOnFailureListener {
                error()
            }
    }

    private fun error() = shortShowToast("통계를 저장하는데 오류가 발생했습니다")

    private fun setLoveMsgTxt(msg: String) {
        binding.loveTxt.text = msg
    }

    fun View.clickBackMainBtn(){
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }
}