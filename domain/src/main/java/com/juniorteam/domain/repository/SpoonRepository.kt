package com.juniorteam.domain.repository

import com.juniorteam.domain.model.IngredientsResponse
import com.juniorteam.domain.model.ProductsResponse
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.RecipesResponse
import com.juniorteam.domain.model.result.Result

interface SpoonRepository {
    suspend fun getRecipesResults(query: String) : Result<RecipesResponse>
    suspend fun getIngredientsResults(query: String) : Result<IngredientsResponse>
    suspend fun getProductsResults(query: String) : Result<ProductsResponse>
    suspend fun getRecipeById(id: String) : Result<RecipeDetails>
}