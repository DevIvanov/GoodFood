package com.juniorteam.data.source.remote.api

import com.juniorteam.data.constants.ApiConstants.GET_CARS
import com.juniorteam.data.constants.ApiConstants.HEADER
import com.juniorteam.data.model.entity.CarEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface CarsApi {


    @Headers(HEADER)
    @GET(GET_CARS)
    fun getCars(): Single<List<CarEntity>>

}
