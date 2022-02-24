package com.juniorteam.goodfood.di

import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.repository.SpoonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [
    NetworkModule::class,
    DatabaseModule::class])
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideSpoonRepository(
        api: SpoonApi
    ): SpoonRepository = SpoonRepositoryImpl(api)
}