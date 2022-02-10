package com.juniorteam.data.source.remote.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.model.Recipe
import retrofit2.HttpException
import java.io.IOException
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
        ).liveData

    fun getRecipes(query: String): LiveData<List<Recipe>> {
        val response = api.getRecipes(query)
        return response.results as LiveData<List<Recipe>>
    }
}