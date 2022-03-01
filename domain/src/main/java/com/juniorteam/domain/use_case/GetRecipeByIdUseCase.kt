package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getRecipeById(id: String): Result<RecipeDetails> {
        return repository.getRecipeById(id)
    }
}