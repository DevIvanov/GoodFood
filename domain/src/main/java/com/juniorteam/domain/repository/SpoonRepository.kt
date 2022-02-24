package com.juniorteam.domain.repository

import androidx.paging.PagingData
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.result.Result
import kotlinx.coroutines.flow.Flow

interface SpoonRepository {
    fun getRecipesResults(query: String) : Flow<PagingData<Recipe>>
    fun getIngredientsResults(query: String) : Flow<PagingData<Ingredient>>
    fun getProductsResults(query: String) : Flow<PagingData<Product>>
    suspend fun getRecipeById(id: String) : Result<RecipeDetails>
}