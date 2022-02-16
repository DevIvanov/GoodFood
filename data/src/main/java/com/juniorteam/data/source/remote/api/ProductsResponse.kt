package com.juniorteam.data.source.remote.api

import com.juniorteam.domain.model.Product

data class ProductsResponse(
    val results : List<Product>,
    val offset : Int,
    val number : Int,
    val totalResults : Int
)