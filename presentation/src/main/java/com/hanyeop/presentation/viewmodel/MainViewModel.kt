package com.hanyeop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.DomainLoveResponse
import com.hanyeop.domain.model.DomainScore
import com.hanyeop.domain.usecase.*
import com.hanyeop.domain.utils.ErrorType
import com.hanyeop.domain.utils.RemoteErrorEmitter
import com.hanyeop.domain.utils.ScreenState
import com.hanyeop.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val setStatisticsUseCase: SetStatisticsUseCase,
    private val setScoreUseCase: SetScoreUseCase,
    private val getScoreUseCase: GetScoreUseCase
): ViewModel(), RemoteErrorEmitter{

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private var _apiCallEvent = SingleLiveEvent<ScreenState>()

    val getStatisticsDisplayEvent: LiveData<Int> get() = _getStatisticsDisplayEvent
    private var _getStatisticsDisplayEvent = SingleLiveEvent<Int>()

    val getScoreEvent: LiveData<Any> get() = _getScoreEvent
    private var _getScoreEvent = SingleLiveEvent<Any>()

    var apiCallResult = DomainLoveResponse("", "", 0, "")
    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"
    var manNameResult = "manEx"
    var womanNameResult = "womanEx"
    var scoreList = arrayListOf<DomainScore>()

    fun checkLoveCalculator(
        host: String,
        key: String,
        mName: String,
        wName: String) = viewModelScope.launch {
            checkLoveCalculatorUseCase.execute(
                this@MainViewModel,host,key,mName,wName).let { response ->

                // 반환값이 null이 아니고 잘 받아왔을 때
                if(response != null){
                    apiCallResult = response
                    _apiCallEvent.postValue(ScreenState.LOADING)
                }
                // 반환값이 null 일 때
                else{
                    _apiCallEvent.postValue(ScreenState.ERROR)
                }
            }
    }

    fun getStatistics() = getStatisticsUseCase.execute()

    fun setStatistics(plusValue : Int) = setStatisticsUseCase.execute(plusValue)

    fun getStatisticsDisplay() = getStatisticsUseCase.execute()
        .addOnSuccessListener {
            _getStatisticsDisplayEvent.value = it.value.toString().toInt()
        }

    fun getScore() = getScoreUseCase.execute()
        .addOnSuccessListener { snapshot ->
            scoreList.clear()
            for (item in snapshot.documents) {
                item.toObject(DomainScore::class.java).let {
                    scoreList.add(it!!)
                }
            }
            _getScoreEvent.call()
        }

    fun setScore(man: String, woman: String, percentage: Int, date: String) {
        setScoreUseCase.execute(DomainScore(man, woman, percentage, date))
    }

    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}