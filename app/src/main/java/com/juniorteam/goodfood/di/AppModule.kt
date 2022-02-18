package com.juniorteam.goodfood.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideSpoonRepositoryImpl(
//        api: SpoonApi
//    ): SpoonRepositoryImpl {
//        return SpoonRepositoryImpl(api)
//    }
}