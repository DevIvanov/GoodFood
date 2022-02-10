package com.juniorteam.data.repository

import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject


class SpoonRepositoryImpl @Inject constructor(private val api: SpoonApi): SpoonRepository {

//    override suspend fun fetchCarList(): Result<List<Car>> {
//        return safeApiCall { carsApi.getCars().mapToCarsList() }
//    }

}