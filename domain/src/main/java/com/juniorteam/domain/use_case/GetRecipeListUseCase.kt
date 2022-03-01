package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.RecipesResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetRecipeListUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getRecipeList(query: String): Result<RecipesResponse> {
        return repository.getRecipesResults(query)
    }
}