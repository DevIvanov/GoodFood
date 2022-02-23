package com.juniorteam.data.source.local.dao

import androidx.room.*
import com.juniorteam.data.constants.DatabaseConstants.QUERY_DELETE_INGREDIENTS
import com.juniorteam.data.constants.DatabaseConstants.QUERY_READ_INGREDIENTS
import com.juniorteam.data.model.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query(QUERY_READ_INGREDIENTS)
    fun readAllData(): Flow<List<IngredientEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: IngredientEntity)

    @Delete
    fun delete(item: IngredientEntity)

    @Insert
    fun insertAll(items: List<IngredientEntity>)

    @Query(QUERY_DELETE_INGREDIENTS)
    suspend fun deleteAll()
}