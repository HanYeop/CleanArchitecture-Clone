package com.hanyeop.data.repository.remote.repositoryimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.hanyeop.data.repository.remote.datasource.SplashDataSource
import com.hanyeop.domain.repository.SplashRepository
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val splashDataSource: SplashDataSource
) : SplashRepository {

    override fun checkAppVersion(): Task<DataSnapshot> {
        return splashDataSource.checkAppVersion()
    }
}