package com.juniorteam.goodfood.ui.screens.bottom_nav_bar

import androidx.annotation.StringRes
import com.juniorteam.goodfood.R

sealed class ScreenNavBar(val route: String, @StringRes val resourceId: Int, val iconId: Int) {
    object Recipes : ScreenNavBar("recipes", R.string.recipes, R.drawable.ic_food_dinner)
    object Ingredients: ScreenNavBar("ingredients", R.string.ingredients, R.drawable.ic_fastfood)
    object Products : ScreenNavBar("products", R.string.products, R.drawable.ic_fitness_center)
}