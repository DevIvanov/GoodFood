package com.juniorteam.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.data.source.remote.data.IngredientsPagingSource
import com.juniorteam.data.source.remote.data.ProductsPagingSource
import com.juniorteam.data.source.remote.data.RecipesPagingSource
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpoonRepositoryImpl @Inject constructor(private val api: SpoonApi): SpoonRepository {

    override fun getRecipesResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RecipesPagingSource(api, query) }
        ).flow

    override fun getIngredientsResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { IngredientsPagingSource(api, query) }
        ).flow

    override fun getProductsResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductsPagingSource(api, query) }
        ).flow
}