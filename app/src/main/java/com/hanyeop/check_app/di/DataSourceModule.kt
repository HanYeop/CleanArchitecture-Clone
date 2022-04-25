package com.hanyeop.check_app.di

import com.hanyeop.data.remote.api.LoveCalculatorApi
import com.hanyeop.data.repository.remote.datasource.MainDataSource
import com.hanyeop.data.repository.remote.datasourceimpl.MainDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideMainDataSource(
        loveCalculatorApi: LoveCalculatorApi,
    ) : MainDataSource {
        return MainDataSourceImpl(
            loveCalculatorApi,
        )
    }
}