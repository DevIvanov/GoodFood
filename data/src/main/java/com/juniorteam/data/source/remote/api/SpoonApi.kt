package com.juniorteam.data.source.remote.api

import com.juniorteam.data.constants.ApiConstants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpoonApi {

    @Headers(ApiConstants.HEADER)
    @GET(ApiConstants.GET_RECIPES)
    fun getRecipes(
        @Query("query") query: String,
    ): RecipesResponse//Single<RecipesResponse>
}