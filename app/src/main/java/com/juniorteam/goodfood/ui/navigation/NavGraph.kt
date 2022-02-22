package com.juniorteam.goodfood.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juniorteam.goodfood.ui.screens.ingredients.IngredientsViewModel
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.navigation_bottom_bar.NavigationBottomBar
import com.juniorteam.goodfood.ui.screens.products.ProductsViewModel
import com.juniorteam.goodfood.ui.screens.recipes.RecipesViewModel
import com.juniorteam.goodfood.ui.navigation.nav_objects.Screen
import com.juniorteam.goodfood.ui.screens.recipe_details.RecipeDetailsScreen
import com.juniorteam.goodfood.ui.screens.splash.AnimatedSplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    recipesViewModel: RecipesViewModel,
    ingredientsViewModel: IngredientsViewModel,
    productsViewModel: ProductsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.BottomNavBar.route) {
            NavigationBottomBar(
                recipesViewModel = recipesViewModel,
                ingredientsViewModel = ingredientsViewModel,
                productsViewModel = productsViewModel,
                externalNavGraph = navController
            )
        }
        composable(route = Screen.RecipeDetails.route) {
            RecipeDetailsScreen()
        }
    }
}