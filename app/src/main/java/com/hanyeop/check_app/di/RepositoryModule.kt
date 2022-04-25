package com.hanyeop.check_app.di

import com.hanyeop.data.repository.remote.datasource.MainDataSource
import com.hanyeop.data.repository.remote.datasource.SplashDataSource
import com.hanyeop.data.repository.remote.repositoryimpl.MainRepositoryImpl
import com.hanyeop.data.repository.remote.repositoryimpl.SplashRepositoryImpl
import com.hanyeop.domain.repository.MainRepository
import com.hanyeop.domain.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMainRepository(
        mainDataSource : MainDataSource
    ): MainRepository {
        return MainRepositoryImpl(
            mainDataSource
        )
    }

    @Provides
    fun provideSplashRepository(
        splashDataSource: SplashDataSource
    ): SplashRepository {
        return SplashRepositoryImpl(
            splashDataSource
        )
    }
}