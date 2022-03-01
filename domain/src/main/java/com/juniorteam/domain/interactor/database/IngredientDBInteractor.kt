package com.juniorteam.domain.interactor.database

import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.use_case.IngredientUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IngredientDBInteractor @Inject constructor(val useCase: IngredientUseCase) {

    fun readAll(): Flow<List<Ingredient>> {
        return useCase.readAll()
    }

    suspend fun insert(item: Ingredient) {
        useCase.insert(item)
    }

    suspend fun delete(item: Ingredient) {
        useCase.delete(item)
    }

    suspend fun insertAll(list: List<Ingredient>) {
        useCase.insertAll(list)
    }

    suspend fun deleteAll() {
        useCase.deleteAll()
    }
}