package com.juniorteam.goodfood.ui.screens.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.PagingData
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.model.Recipe
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.ingredients.IngredientsViewModel
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.navigation_bottom_bar.NavigationBottomBar
import com.juniorteam.goodfood.ui.screens.splash.AnimatedSplashScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    recipesList: Flow<PagingData<Recipe>>,
    ingredientsViewModel: IngredientsViewModel,
    productsList: Flow<PagingData<Product>>,
    context: Context
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
                recipesList = recipesList,
                ingredientsViewModel = ingredientsViewModel,
                productsList = productsList,
                context = context
            )
        }
    }
}