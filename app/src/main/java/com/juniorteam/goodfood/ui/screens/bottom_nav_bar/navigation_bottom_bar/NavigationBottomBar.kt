package com.juniorteam.goodfood.ui.screens.bottom_nav_bar.navigation_bottom_bar

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.model.Recipe
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.ScreenNavBar
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.ingredients.IngredientsScreen
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.products.ProductsScreen
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.recipes.RecipesScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun NavigationBottomBar(
    recipesList: Flow<PagingData<Recipe>>,
    ingredientList: Flow<PagingData<Ingredient>>,
    productsList: Flow<PagingData<Product>>,
    context: Context
) {
    val items = listOf(
        ScreenNavBar.Recipes,
        ScreenNavBar.Ingredients,
        ScreenNavBar.Products
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(screen.iconId), contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = ScreenNavBar.Ingredients.route, Modifier.padding(innerPadding)) {
            composable(ScreenNavBar.Recipes.route) { RecipesScreen().RecipesList(recipesList = recipesList, context = context) }
            composable(ScreenNavBar.Ingredients.route) { IngredientsScreen().IngredientsList(ingredientsList = ingredientList, context = context)}
            composable(ScreenNavBar.Products.route){ ProductsScreen().ProductsList(productList = productsList, context = context) }
        }
    }
}
