package com.juniorteam.data.source.remote.api

import com.juniorteam.data.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpoonApi {

    @Headers(ApiConstants.HEADER, ApiConstants.API_KEY)
    @GET(ApiConstants.GET_RECIPES)
    fun getRecipes(
        @Query(ApiConstants.QUERY_QUERY) query: String,
    ): RecipesResponse
}