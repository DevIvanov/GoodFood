package com.juniorteam.data.source.remote.api

import com.juniorteam.domain.model.Ingredient

data class IngredientsResponse(
    val results : List<Ingredient>,
    val offset : Int,
    val number : Int,
    val totalResults : Int
)