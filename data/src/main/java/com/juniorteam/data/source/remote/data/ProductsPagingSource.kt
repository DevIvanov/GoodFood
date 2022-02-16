package com.juniorteam.data.source.remote.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juniorteam.data.source.remote.api.SpoonApi
import com.juniorteam.domain.model.Product
import retrofit2.HttpException
import java.io.IOException

private const val PRODUCTS_STARTING_PAGE_INDEX = 1

class ProductsPagingSource(
    private val api: SpoonApi,
    private val query: String
) : PagingSource<Int, Product>() {

    private val tag = ProductsPagingSource::class.java.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: PRODUCTS_STARTING_PAGE_INDEX

        return try {
            val response = api.getProducts(query = query)
            Log.e(tag, response.toString())
            val products = response.results
            total = response.totalResults.toString()

            LoadResult.Page(
                data = products,
                prevKey = if (position == PRODUCTS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (products.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(tag, exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object{
        var total: String? = ""
    }
}