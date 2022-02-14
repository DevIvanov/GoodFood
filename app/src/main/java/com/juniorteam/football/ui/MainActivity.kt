package com.juniorteam.football.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juniorteam.football.ui.screens.RecipesScreen
import com.juniorteam.football.ui.screens.Screen
import com.juniorteam.football.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = MainActivity::class.java.simpleName

    private val recipesViewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            Navigation()
        }
    }


    @Composable
    fun Navigation() {
        val items = listOf(
            Screen.Recipes,
            Screen.FriendsList,
            Screen.FoodList
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
            NavHost(navController, startDestination = Screen.Recipes.route, Modifier.padding(innerPadding)) {
                composable(Screen.Recipes.route) { Recipes(navController) }
                composable(Screen.FriendsList.route) { FriendsList(navController)}
                composable(Screen.FoodList.route){ FoodList(navController) }
            }
        }
    }

    @Composable
    fun Recipes(navController: NavController) {
        RecipesScreen().RecipesList(recipesList = recipesViewModel.recipesList, context = this)
    }

    @Composable
    fun FriendsList(navController: NavController) {
        Button(onClick = { navController.navigate("profile") }) {
            Text(text = "Navigate next 2")
        }
    }

    @Composable
    fun FoodList(navController: NavController) {
        Button(onClick = { navController.navigate("friendsList") }) {
            Text(text = "Navigate next 3")
        }
    }
}

