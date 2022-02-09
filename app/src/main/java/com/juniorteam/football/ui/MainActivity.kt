package com.juniorteam.football.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juniorteam.domain.model.Car
import com.juniorteam.football.R
import com.juniorteam.football.databinding.FragmentSplashBinding
import com.juniorteam.football.ui.screens.viewmodel.CarViewModel
import com.juniorteam.football.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = MainActivity::class.java.simpleName
    private val carViewModel: CarViewModel by viewModels()

    private val carList = mutableStateListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Fragment()
//                    Navigation()
                    AllCars(carList = carList)
                }
            }
        }
        carViewModel.getCarList()
        setupObserver()
    }


    private fun setupObserver() {
        carViewModel.carList.observe(this, Observer { cars->
            carList.clear()
            carList.addAll(cars)
            Log.v(tag, "Success!")
            Log.v(tag, cars.toString())
        })
    }

    @Composable
    fun AllCars(carList: List<Car>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "\uD83C\uDF3F  Plants in Cosmetics",
                            style = MaterialTheme.typography.h3
                        )
                    }
                }
                items(carList) { plant ->
                    PlantCard(plant.make!!, plant.model!!, R.drawable.ic_android)
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
//            Navigation()
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

@Composable
fun PlantCard(name: String, description: String, image: Int) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}