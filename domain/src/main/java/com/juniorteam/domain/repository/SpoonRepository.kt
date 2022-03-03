package com.juniorteam.domain.repository

import com.juniorteam.domain.model.*
import com.juniorteam.domain.model.result.Result
import retrofit2.Call

interface SpoonRepository {
    suspend fun getRecipesResults(query: String): Call<RecipesResponse>
    suspend fun getIngredientsResults(query: String): Result<IngredientsResponse>
    suspend fun getProductsResults(query: String): Result<ProductsResponse>
    suspend fun getRecipeById(id: String): Result<RecipeDetails>
    suspend fun getProductById(id: String): Result<ProductDetails>
    suspend fun getIngredientById(id: String): Result<IngredientDetails>
}