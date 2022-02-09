package com.juniorteam.data.repository

import com.juniorteam.data.source.remote.api.CarsApi
import com.juniorteam.domain.repository.VehicleRepository
import javax.inject.Inject


class VehicleRepositoryImpl @Inject constructor(private val carsApi: CarsApi): VehicleRepository {

//    override suspend fun fetchCarList(): Result<List<Car>> {
//        return safeApiCall { carsApi.getCars().mapToCarsList() }
//    }

}