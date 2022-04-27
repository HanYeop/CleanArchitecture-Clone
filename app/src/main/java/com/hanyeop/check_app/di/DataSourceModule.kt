package com.hanyeop.check_app.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.hanyeop.data.remote.api.LoveCalculatorApi
import com.hanyeop.data.repository.remote.datasource.MainDataSource
import com.hanyeop.data.repository.remote.datasource.SplashDataSource
import com.hanyeop.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.hanyeop.data.repository.remote.datasourceimpl.SplashDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        loveCalculatorApi: LoveCalculatorApi,
        firebaseRtdb : FirebaseDatabase,
        fireStore : FirebaseFirestore
    ) : MainDataSource {
        return MainDataSourceImpl(
            loveCalculatorApi,
            firebaseRtdb,
            fireStore
        )
    }

    @Provides
    @Singleton
    fun provideSplashDataSource(
        firebaseRtdb : FirebaseDatabase,
        fireStore : FirebaseFirestore
    ) : SplashDataSource {
        return SplashDataSourceImpl(
            firebaseRtdb = firebaseRtdb, fireStore = fireStore
        )
    }
}