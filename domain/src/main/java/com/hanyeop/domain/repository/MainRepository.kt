package com.hanyeop.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.hanyeop.domain.model.DomainLoveResponse
import com.hanyeop.domain.utils.RemoteErrorEmitter

interface MainRepository {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DomainLoveResponse?

    // 통계 가져오기
    fun getStatistics() : Task<DataSnapshot>

    // 통계 저장하기
    fun setStatistics(plusValue : Int) : Task<Void>
}