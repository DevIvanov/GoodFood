package com.juniorteam.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatabaseRepository<T> {
    fun readAll(): Flow<List<T>>
    suspend fun delete(item: T)
    suspend fun insert(item: T)
    suspend fun deleteAll()
    suspend fun insertAll(items: List<T>)
}