package com.juniorteam.domain.interactor

import com.juniorteam.domain.model.IngredientDetails
import com.juniorteam.domain.model.IngredientsResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.use_case.GetIngredientByIdUseCase
import com.juniorteam.domain.use_case.GetIngredientListUseCase
import javax.inject.Inject

class IngredientInteractor @Inject constructor(
    private val getIngredientListUseCase: GetIngredientListUseCase,
    private val getIngredientByIdUseCase: GetIngredientByIdUseCase
) {

    suspend fun getIngredientList(query: String): Result<IngredientsResponse> {
        return getIngredientListUseCase.getIngredientList(query)
    }

    suspend fun getIngredientById(id: String): Result<IngredientDetails> {
        return getIngredientByIdUseCase.getIngredientById(id)
    }
}