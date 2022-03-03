package com.juniorteam.domain.interactor

import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.RecipesResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.use_case.GetRecipeByIdUseCase
import com.juniorteam.domain.use_case.GetRecipeListUseCase
import retrofit2.Call
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val getRecipeListUseCase: GetRecipeListUseCase
) {

    suspend fun getRecipeList(query: String): Call<RecipesResponse> {
        return getRecipeListUseCase.getRecipeList(query)
    }

    suspend fun getRecipeById(id: String): Result<RecipeDetails> {
        return getRecipeByIdUseCase.getRecipeById(id)
    }
}