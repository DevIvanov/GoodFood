package com.juniorteam.data.source.remote.api

import com.juniorteam.domain.model.Product

data class ProductsResponse(
    val type : String,
    val products : List<Product>,
    val offset : Int,
    val number : Int,
    val totalProducts : Int,
    val processingTimeMs : Int,
    val expires : Long,
    val isStale : Boolean
)