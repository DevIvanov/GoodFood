package com.juniorteam.goodfood.ui.navigation.nav_objects

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object BottomNavBar : Screen("bottom_nav_bar")
    object RecipeDetails : Screen("recipe_details")
}
