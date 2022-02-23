package com.juniorteam.data.repository

import com.juniorteam.data.mapper.RecipeModelMapperImpl
import com.juniorteam.data.model.entity.RecipeEntity
import com.juniorteam.data.source.local.dao.RecipeDao
import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeDao: RecipeDao,
    private val mapper: RecipeModelMapperImpl
) : DatabaseRepository<Recipe>{


    override fun readAll(): Flow<List<Recipe>> {
        return recipeDao.readAllData().map { it.map(mapper::fromEntity) }
    }

    override suspend fun delete(item: Recipe) {
        return recipeDao.delete(mapper.toEntity(item))
    }

    override suspend fun insert(item: Recipe) {
        return recipeDao.insert(mapper.toEntity(item))
    }

    override suspend fun deleteAll() {
        recipeDao.deleteAll()
    }

    override suspend fun insertAll(list: List<Recipe>) {
        val recipeEntity: List<RecipeEntity> = list.map { mapper.toEntity(it) }
        recipeDao.insertAll(recipeEntity)
    }
}