package com.juniorteam.domain.interactor.database

import com.juniorteam.domain.model.Product
import com.juniorteam.domain.use_case.ProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDBInteractor @Inject constructor(val useCase: ProductUseCase) {

    fun readAll(): Flow<List<Product>> {
        return useCase.readAll()
    }

    suspend fun insert(item: Product) {
        useCase.insert(item)
    }

    suspend fun delete(item: Product) {
        useCase.delete(item)
    }

    suspend fun insertAll(list: List<Product>) {
        useCase.insertAll(list)
    }

    suspend fun deleteAll() {
        useCase.deleteAll()
    }
}