package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.ProductsResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getProductList(query: String): Result<ProductsResponse> {
        return repository.getProductsResults(query)
    }
}