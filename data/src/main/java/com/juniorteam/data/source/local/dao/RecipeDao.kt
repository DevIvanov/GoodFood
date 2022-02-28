package com.juniorteam.data.source.local.dao

import androidx.room.*
import com.juniorteam.data.constants.DatabaseConstants.QUERY_DELETE_RECIPES
import com.juniorteam.data.constants.DatabaseConstants.QUERY_READ_RECIPES
import com.juniorteam.data.model.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query(QUERY_READ_RECIPES)
    fun readAllData(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: RecipeEntity)

    @Delete
    fun delete(item: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<RecipeEntity>)

    @Query(QUERY_DELETE_RECIPES)
    suspend fun deleteAll()
}