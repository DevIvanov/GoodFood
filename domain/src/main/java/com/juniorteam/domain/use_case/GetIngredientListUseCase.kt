package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.IngredientsResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetIngredientListUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getIngredientList(query: String): Result<IngredientsResponse> {
        return repository.getIngredientsResults(query)
    }
}