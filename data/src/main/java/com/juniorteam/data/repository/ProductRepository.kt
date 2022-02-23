package com.juniorteam.data.repository

import com.juniorteam.data.mapper.ProductModelMapperImpl
import com.juniorteam.data.model.entity.ProductEntity
import com.juniorteam.data.source.local.dao.ProductDao
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val mapper: ProductModelMapperImpl
) : DatabaseRepository<Product>{


    override fun readAll(): Flow<List<Product>> {
        return productDao.readAllData().map { it.map(mapper::fromEntity) }
    }

    override suspend fun delete(item: Product) {
        return productDao.delete(mapper.toEntity(item))
    }

    override suspend fun insert(item: Product) {
        return productDao.insert(mapper.toEntity(item))
    }

    override suspend fun deleteAll() {
        productDao.deleteAll()
    }

    override suspend fun insertAll(list: List<Product>) {
        val productEntity: List<ProductEntity> = list.map { mapper.toEntity(it) }
        productDao.insertAll(productEntity)
    }
}