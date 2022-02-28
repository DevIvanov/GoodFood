package com.juniorteam.data.source.remote.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.model.Recipe
import retrofit2.HttpException
import java.io.IOException

private const val RECIPES_STARTING_PAGE_INDEX = 1

class RecipesPagingSource(
    private val api: SpoonApi,
    private val query: String
) : PagingSource<Int, Recipe>() {

    private val tag = RecipesPagingSource::class.java.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val position = params.key ?: RECIPES_STARTING_PAGE_INDEX

        return try {
            val response = api.getRecipes(query = query)
            val recipes = response.results
            total = response.totalResults.toString()
            Log.i(tag, "recipes response = $response")

            LoadResult.Page(
                data = recipes,
                prevKey = if (position == RECIPES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (recipes.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: Exception){
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object{
        var total: String? = ""
    }
}