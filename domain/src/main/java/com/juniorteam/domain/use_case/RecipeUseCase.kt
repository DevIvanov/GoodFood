package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RecipeUseCase @Inject constructor(private val repository: DatabaseRepository<Recipe>) {

    fun readAll(): Flow<List<Recipe>> {
        return repository.readAll()
    }

    suspend fun insert (item: Recipe) {
        repository.insert(item)
    }

    suspend fun delete(item: Recipe) {
        repository.delete(item)
    }

    suspend fun insertAll(list: List<Recipe>) {
        repository.insertAll(list)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}