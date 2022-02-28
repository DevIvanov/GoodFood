package com.juniorteam.domain.model

data class RecipesResponse(
    val results : List<Recipe>,
    val offset : Int,
    val number : Int,
    val totalResults : Int
)