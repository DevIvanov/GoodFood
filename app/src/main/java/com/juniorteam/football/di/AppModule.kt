package com.juniorteam.football.di

import com.juniorteam.data.repository.VehicleRepositoryImpl
import com.juniorteam.data.source.remote.api.CarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideVehicleRepositoryImpl(
        carsApi: CarsApi
    ): VehicleRepositoryImpl {
        return VehicleRepositoryImpl(carsApi)
    }
}