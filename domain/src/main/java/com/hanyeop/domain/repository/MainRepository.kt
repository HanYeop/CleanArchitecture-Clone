package com.hanyeop.domain.repository

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
}