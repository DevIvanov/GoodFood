package com.juniorteam.football.ui.screens

import androidx.annotation.StringRes
import com.juniorteam.football.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconId: Int) {
    object Recipes : Screen("recipes", R.string.recipes, R.drawable.ic_food_dinner)
    object FriendsList : Screen("friendslist", R.string.friendsList, R.drawable.ic_fastfood)
    object FoodList : Screen("foodlist", R.string.foodList, R.drawable.ic_fitness_center)
}