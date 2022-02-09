package com.juniorteam.football.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juniorteam.football.databinding.FragmentSplashBinding
import com.juniorteam.football.ui.theme.MyApplicationTheme
import com.juniorteam.football.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Fragment()
                    Navigation()
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
//            Greeting("Android")
//            Fragment()
            Navigation()
        }
    }

    @Composable
    fun Fragment() {
        AndroidView(
            modifier =
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            factory = { context ->
                FrameLayout(context).apply {
                    FragmentSplashBinding.inflate(LayoutInflater.from(context), this, true).root
                }
            })
    }

    @Composable
    fun Navigation() {
        val items = listOf(
            Screen.Profile,
            Screen.FriendsList,
        )
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
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
            NavHost(navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
                composable(Screen.Profile.route) { Profile(navController) }
                composable(Screen.FriendsList.route) { FriendsList(navController) }
            }
        }
    }

    @Composable
    fun Profile(navController: NavController) {
        Button(onClick = { navController.navigate("friendslist") }) {
            Text(text = "Navigate next")
        }
    }

    @Composable
    fun FriendsList(navController: NavController) {
        Button(onClick = { navController.navigate("profile") }) {
            Text(text = "Navigate next 2")
        }
    }

}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friendsList)
}