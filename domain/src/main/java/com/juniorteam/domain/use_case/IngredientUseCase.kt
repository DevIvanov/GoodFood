package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class IngredientUseCase @Inject constructor(private val repository: DatabaseRepository<Ingredient>) {
    fun readAll(): Flow<List<Ingredient>> {
        return repository.readAll()
    }

    suspend fun insert (article: Ingredient) {
        repository.insert(article)
    }

    suspend fun delete(article: Ingredient) {
        repository.delete(article)
    }

    suspend fun insertAll(list: List<Ingredient>) {
        repository.insertAll(list)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}