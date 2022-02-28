package com.juniorteam.domain.repository

import androidx.paging.PagingData
import com.juniorteam.domain.model.*
import com.juniorteam.domain.model.result.Result
import kotlinx.coroutines.flow.Flow

interface SpoonRepository {
    suspend fun getRecipesResults(query: String) : Result<RecipesResponse>
    fun getIngredientsResults(query: String) : Flow<PagingData<Ingredient>>
    fun getProductsResults(query: String) : Flow<PagingData<Product>>
    suspend fun getRecipeById(id: String) : Result<RecipeDetails>
}