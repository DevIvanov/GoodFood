package com.juniorteam.domain.model

data class IngredientsResponse(
    val results : List<Ingredient>,
    val offset : Int,
    val number : Int,
    val totalResults : Int
)