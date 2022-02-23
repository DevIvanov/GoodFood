package com.juniorteam.data.source.local.dao

import androidx.room.*
import com.juniorteam.data.constants.DatabaseConstants.QUERY_DELETE_PRODUCTS
import com.juniorteam.data.constants.DatabaseConstants.QUERY_READ_PRODUCTS
import com.juniorteam.data.model.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query(QUERY_READ_PRODUCTS)
    fun readAllData(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: ProductEntity)

    @Delete
    fun delete(item: ProductEntity)

    @Insert
    fun insertAll(items: List<ProductEntity>)

    @Query(QUERY_DELETE_PRODUCTS)
    suspend fun deleteAll()
}