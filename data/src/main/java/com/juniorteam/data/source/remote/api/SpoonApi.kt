package com.juniorteam.data.source.remote.api

import com.juniorteam.data.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpoonApi {

    @Headers(ApiConstants.HEADER)

    @GET(ApiConstants.GET_RECIPES)
    suspend fun getRecipes(
        @Query(ApiConstants.QUERY_API_KEY) apiKey: String = ApiConstants.API_KEY_VALUE,
        @Query(ApiConstants.QUERY_QUERY) query: String,
    ): RecipesResponse

    @GET(ApiConstants.GET_INGREDIENTS)
    suspend fun getIngredients(
        @Query(ApiConstants.QUERY_API_KEY) apiKey: String = ApiConstants.API_KEY_VALUE,
        @Query(ApiConstants.QUERY_QUERY) query: String,
    ): IngredientsResponse

    @GET(ApiConstants.GET_PRODUCTS)
    suspend fun getProducts(
        @Query(ApiConstants.QUERY_API_KEY) apiKey: String = ApiConstants.API_KEY_VALUE,
        @Query(ApiConstants.QUERY_QUERY) query: String,
    ): ProductsResponse
}