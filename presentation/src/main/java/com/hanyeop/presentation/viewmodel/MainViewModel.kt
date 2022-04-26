package com.hanyeop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.DomainLoveResponse
import com.hanyeop.domain.usecase.CheckLoveCalculatorUseCase
import com.hanyeop.domain.usecase.GetStatisticsUseCase
import com.hanyeop.domain.usecase.SetStatisticsUseCase
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
    private val setStatisticsUseCase: SetStatisticsUseCase
): ViewModel(), RemoteErrorEmitter{

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private var _apiCallEvent = SingleLiveEvent<ScreenState>()

    val getStatisticsDisplayEvent: LiveData<Int> get() = _getStatisticsDisplayEvent
    private var _getStatisticsDisplayEvent = SingleLiveEvent<Int>()

    var apiCallResult = DomainLoveResponse("", "", 0, "")
    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"
    var manNameResult = "manEx"
    var womanNameResult = "womanEx"

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

    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}