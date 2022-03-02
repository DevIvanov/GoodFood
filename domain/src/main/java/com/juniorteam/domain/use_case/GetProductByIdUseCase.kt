package com.juniorteam.domain.use_case

import com.juniorteam.domain.model.ProductDetails
import com.juniorteam.domain.model.result.Result
import com.juniorteam.domain.repository.SpoonRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val repository: SpoonRepository) {

    suspend fun getProductById(id: String): Result<ProductDetails> {
        return repository.getProductById(id)
    }
}