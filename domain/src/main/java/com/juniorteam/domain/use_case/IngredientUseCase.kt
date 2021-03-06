package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class IngredientUseCase @Inject constructor(private val repository: DatabaseRepository<Ingredient>) {

    fun readAll(): Flow<List<Ingredient>> {
        return repository.readAll()
    }

    suspend fun insert(item: Ingredient) {
        repository.insert(item)
    }

    suspend fun delete(item: Ingredient) {
        repository.delete(item)
    }

    suspend fun insertAll(list: List<Ingredient>) {
        repository.insertAll(list)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}