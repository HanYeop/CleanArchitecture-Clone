package com.hanyeop.data.repository.remote.datasource

import com.hanyeop.data.remote.model.DataLoveResponse
import com.hanyeop.domain.utils.RemoteErrorEmitter

interface MainDataSource {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DataLoveResponse?
}