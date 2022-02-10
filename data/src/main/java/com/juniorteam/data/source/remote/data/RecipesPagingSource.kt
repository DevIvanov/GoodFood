package com.juniorteam.data.source.remote.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juniorteam.data.model.entity.RecipeEntity
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.model.Recipe
import retrofit2.HttpException
import java.io.IOException

private const val RECIPES_STARTING_PAGE_INDEX = 1

class RecipesPagingSource(
    private val api: SpoonApi,
    private val query: String
) : PagingSource<Int, Recipe>() {

    companion object{
        var total: String? = ""
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val position = params.key ?: RECIPES_STARTING_PAGE_INDEX

        return try {
            val response = api.getRecipes(query)
            val photos = response.results
            total = response.totalResults.toString()

            LoadResult.Page(
                data = photos,
                prevKey = if (position == RECIPES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}