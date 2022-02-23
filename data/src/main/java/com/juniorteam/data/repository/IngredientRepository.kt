package com.juniorteam.data.repository

import com.juniorteam.data.mapper.IngredientModelMapperImpl
import com.juniorteam.data.model.entity.IngredientEntity
import com.juniorteam.data.source.local.dao.IngredientDao
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val mapper: IngredientModelMapperImpl
) : DatabaseRepository<Ingredient>{


    override fun readAll(): Flow<List<Ingredient>> {
        return ingredientDao.readAllData().map { it.map(mapper::fromEntity) }
    }

    override suspend fun delete(item: Ingredient) {
        return ingredientDao.delete(mapper.toEntity(item))
    }

    override suspend fun insert(item: Ingredient) {
        return ingredientDao.insert(mapper.toEntity(item))
    }

    override suspend fun deleteAll() {
        ingredientDao.deleteAll()
    }

    override suspend fun insertAll(list: List<Ingredient>) {
        val ingredientEntity: List<IngredientEntity> = list.map { mapper.toEntity(it) }
        ingredientDao.insertAll(ingredientEntity)
    }
}