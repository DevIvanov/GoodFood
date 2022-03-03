package com.juniorteam.data.repository

import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.data.util.safeApiCall
import com.juniorteam.domain.model.*
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpoonRepositoryImpl @Inject constructor(private val api: SpoonApi) : SpoonRepository {

    override suspend fun getRecipesResults(query: String): Call<RecipesResponse> {
        return api.getRecipes(query = query)
    }

    override suspend fun getIngredientsResults(query: String): Result<IngredientsResponse> {
        return safeApiCall { api.getIngredients(query = query) }
    }

    override suspend fun getProductsResults(query: String): Result<ProductsResponse> {
        return safeApiCall { api.getProducts(query = query) }
    }

    override suspend fun getRecipeById(id: String): Result<RecipeDetails> {
        return safeApiCall { api.getRecipeById(id) }
    }

    override suspend fun getProductById(id: String): Result<ProductDetails> {
        return safeApiCall { api.getProductById(id) }
    }

    override suspend fun getIngredientById(id: String): Result<IngredientDetails> {
        return safeApiCall { api.getIngredientById(id) }
    }
}