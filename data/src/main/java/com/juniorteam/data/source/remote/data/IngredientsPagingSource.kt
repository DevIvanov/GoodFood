package com.juniorteam.data.source.remote.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.model.Ingredient
import retrofit2.HttpException
import java.io.IOException

private const val INGREDIENTS_STARTING_PAGE_INDEX = 1

class IngredientsPagingSource(
    private val api: SpoonApi,
    private val query: String
) : PagingSource<Int, Ingredient>() {

    private val tag = IngredientsPagingSource::class.java.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ingredient> {
        val position = params.key ?: INGREDIENTS_STARTING_PAGE_INDEX

        return try {
            val response = api.getIngredients(query = query)
            Log.i(tag, "response = $response")
            val ingredients = response.results
            Log.e(tag, "query = $query")
            total = response.totalResults.toString()

            LoadResult.Page(
                data = ingredients,
                prevKey = if (position == INGREDIENTS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (ingredients.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Ingredient>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object{
        var total: String? = ""
    }
}