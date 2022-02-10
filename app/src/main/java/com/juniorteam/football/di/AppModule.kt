package com.juniorteam.football.di

import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.data.source.remote.api.SpoonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSpoonRepositoryImpl(
        api: SpoonApi
    ): SpoonRepositoryImpl {
        return SpoonRepositoryImpl(api)
    }
}