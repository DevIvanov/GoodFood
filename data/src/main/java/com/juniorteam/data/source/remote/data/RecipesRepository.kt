package com.juniorteam.data.source.remote.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.juniorteam.data.source.remote.api.SpoonApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(private val api: SpoonApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RecipesPagingSource(api, query) }
        ).flow
}
