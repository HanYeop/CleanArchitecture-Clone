package com.hanyeop.data.repository.remote.datasourceimpl

import com.hanyeop.data.remote.api.LoveCalculatorApi
import com.hanyeop.data.remote.model.DataLoveResponse
import com.hanyeop.data.repository.remote.datasource.MainDataSource
import com.hanyeop.data.utils.base.BaseDataSource
import com.hanyeop.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val loveCalculatorApi: LoveCalculatorApi
) : BaseDataSource(), MainDataSource {

    override suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DataLoveResponse? {
        return safeApiCall(remoteErrorEmitter){
            loveCalculatorApi.getPercentage(host = host, key = key, fName = wName, sName = mName)
        }?.body()
    }
}