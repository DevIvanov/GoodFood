package com.juniorteam.football.ui.screens

import androidx.annotation.StringRes
import com.juniorteam.football.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconId: Int) {
    object Recipes : Screen("recipes", R.string.recipes, R.drawable.ic_food_dinner)
    object Ingredients: Screen("ingredients", R.string.ingredients, R.drawable.ic_fastfood)
    object Products : Screen("products", R.string.products, R.drawable.ic_fitness_center)
}