package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.Product
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductUseCase @Inject constructor(private val repository: DatabaseRepository<Product>) {

    fun readAll(): Flow<List<Product>> {
        return repository.readAll()
    }

    suspend fun insert(item: Product) {
        repository.insert(item)
    }

    suspend fun delete(item: Product) {
        repository.delete(item)
    }

    suspend fun insertAll(list: List<Product>) {
        repository.insertAll(list)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}