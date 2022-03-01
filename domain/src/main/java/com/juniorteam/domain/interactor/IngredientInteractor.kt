package com.juniorteam.domain.interactor

import com.juniorteam.domain.model.IngredientsResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.use_case.GetIngredientListUseCase
import javax.inject.Inject

class IngredientInteractor @Inject constructor(
    private val getIngredientListUseCase: GetIngredientListUseCase
) {

    suspend fun getIngredientList(query: String): Result<IngredientsResponse> {
        return getIngredientListUseCase.getIngredientList(query = query)
    }
}