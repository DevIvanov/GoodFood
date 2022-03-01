package com.juniorteam.domain.interactor

import com.juniorteam.domain.model.ProductsResponse
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.use_case.GetProductListUseCase
import javax.inject.Inject

class ProductInteractor @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) {

    suspend fun getProductList(query: String): Result<ProductsResponse> {
        return getProductListUseCase.getProductList(query = query)
    }
}