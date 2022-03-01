package com.juniorteam.domain.interactor.database

import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.use_case.RecipeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeDBInteractor @Inject constructor(val useCase: RecipeUseCase) {

    fun readAll(): Flow<List<Recipe>> {
        return useCase.readAll()
    }

    suspend fun insert (item: Recipe) {
        useCase.insert(item)
    }

    suspend fun delete(item: Recipe) {
        useCase.delete(item)
    }

    suspend fun insertAll(list: List<Recipe>) {
        useCase.insertAll(list)
    }

    suspend fun deleteAll() {
        useCase.deleteAll()
    }
}