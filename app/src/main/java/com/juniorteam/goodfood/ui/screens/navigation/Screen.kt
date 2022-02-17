package com.juniorteam.goodfood.ui.screens.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object BottomNavBar : Screen("bottom_nav_bar")
}
