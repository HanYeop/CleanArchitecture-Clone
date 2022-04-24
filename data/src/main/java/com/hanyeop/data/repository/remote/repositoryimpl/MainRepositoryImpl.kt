package com.hanyeop.data.repository.remote.repositoryimpl

import com.hanyeop.data.mapper.MainMapper
import com.hanyeop.data.repository.remote.datasource.MainDataSource
import com.hanyeop.domain.model.DomainLoveResponse
import com.hanyeop.domain.repository.MainRepository
import com.hanyeop.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {
    override suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DomainLoveResponse? {
        return MainMapper.loveMapper(mainDataSource.checkLoveCalculator(remoteErrorEmitter = remoteErrorEmitter, host = host, key = key, mName = mName, wName = wName))
    }
}