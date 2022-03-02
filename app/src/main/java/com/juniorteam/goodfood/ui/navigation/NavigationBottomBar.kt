package com.juniorteam.goodfood.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juniorteam.goodfood.ui.navigation.nav_objects.ScreenNavBar
import com.juniorteam.goodfood.ui.screens.ingredients.IngredientsScreen
import com.juniorteam.goodfood.ui.screens.ingredients.IngredientsViewModel
import com.juniorteam.goodfood.ui.screens.products.ProductsScreen
import com.juniorteam.goodfood.ui.screens.products.ProductsViewModel
import com.juniorteam.goodfood.ui.screens.recipes.RecipesScreen
import com.juniorteam.goodfood.ui.screens.recipes.RecipesViewModel

@Composable
fun NavigationBottomBar(
    recipesViewModel: RecipesViewModel,
    ingredientsViewModel: IngredientsViewModel,
    productsViewModel: ProductsViewModel,
    externalNavGraph: NavHostController
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
        NavHost(navController, startDestination = ScreenNavBar.Recipes.route, Modifier.padding(innerPadding)) {
            composable(ScreenNavBar.Recipes.route) { RecipesScreen(recipesViewModel = recipesViewModel, externalNavGraph = externalNavGraph) }
            composable(ScreenNavBar.Ingredients.route) { IngredientsScreen(ingredientsViewModel = ingredientsViewModel, externalNavGraph = externalNavGraph)}
            composable(ScreenNavBar.Products.route){ ProductsScreen(productsViewModel = productsViewModel, externalNavGraph = externalNavGraph) }
        }
    }
}
