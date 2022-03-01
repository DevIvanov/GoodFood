package com.juniorteam.data.repository

import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.data.util.safeApiCall
import com.juniorteam.domain.model.IngredientsResponse
import com.juniorteam.domain.model.ProductsResponse
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.RecipesResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpoonRepositoryImpl @Inject constructor(private val api: SpoonApi) : SpoonRepository {

    val tag = SpoonRepositoryImpl::class.java.simpleName

    override suspend fun getRecipesResults(query: String): Result<RecipesResponse> {
        return safeApiCall { api.getRecipes(query = query) }
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
}