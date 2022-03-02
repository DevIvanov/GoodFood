package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.IngredientDetails
import com.juniorteam.domain.model.ProductDetails
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetIngredientByIdUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getIngredientById(id: String): Result<IngredientDetails> {
        return repository.getIngredientById(id)
    }
}