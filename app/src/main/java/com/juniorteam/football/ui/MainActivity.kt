package com.juniorteam.football.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.Recipe
import com.juniorteam.football.R
import com.juniorteam.football.databinding.FragmentSplashBinding
import com.juniorteam.football.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = MainActivity::class.java.simpleName
    private val recipesViewModel: RecipesViewModel by viewModels()

    private val recipesList = mutableStateListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
//        recipesViewModel.getRecipeList("potato")
//        setupObserver()
    }

//    private fun setupObserver() {
//        recipesViewModel.recipeList.observe(this, Observer { recipes ->
//            recipesList.clear()
//            recipesList.addAll(recipes)
//            Log.v(tag, "Success!")
//            Log.v(tag, recipes.toString())
//        })
//    }

//    @Composable
//    fun AllRecipes(list: List<Recipe>, navController: NavController) {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    backgroundColor = MaterialTheme.colors.primary,
//                    title = { Text(stringResource(R.string.app_name)) }
//                )
//            }
//        ) {
//            Button(onClick = { navController.navigate("friendslist") }) {
//                Text(text = "Navigate next")
//            }
//            if (recipesList.isEmpty()){
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
//                    CircularProgressIndicator()
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentPadding = PaddingValues(16.dp)
//                ) {
//                    item {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight()
//                                .padding(vertical = 25.dp),
//                            horizontalArrangement = Arrangement.Center,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                "\uD83C\uDF3F  Plants in Cosmetics",
//                                style = MaterialTheme.typography.h3
//                            )
//                        }
//                    }
//                    items(recipesList) { recipe ->
//                        RecipeItem(recipe)
//                    }
//                }
//            }
//        }
//    }

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
            Screen.Profile,
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
            NavHost(navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
                composable(Screen.Profile.route) { Profile(navController) }
                composable(Screen.FriendsList.route) { FriendsList(navController)}
                composable(Screen.FoodList.route){ FoodList(navController) }
            }
        }
    }

    @Composable
    fun RecipesList(modifier: Modifier, recipesList: Flow<PagingData<Recipe>>, context: Context) {
        val recipesItems = recipesList.collectAsLazyPagingItems()

        LazyColumn {
            items(recipesItems) { item ->
                item?.let {
                    RecipeItem(recipesData = item, onClick = {
                        Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                    },
                    )
                }
            }
            recipesItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        //You can add modifier to manage load state when first time response page is loading
                    }
                    loadState.append is LoadState.Loading -> {
                        //You can add modifier to manage load state when next response page is loading
                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message
                    }
                }
            }
        }
    }

    @Composable
    fun UserList(modifier: Modifier = Modifier, viewModel: RecipesViewModel, context: Context) {
        RecipesList(modifier, recipesList = viewModel.recipesList, context)
    }

    @Composable
    fun Profile(navController: NavController) {
//        AllRecipes(list = recipesList, navController)
        UserList(viewModel = recipesViewModel, context = this)
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

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconId: Int) {
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_android)
    object FriendsList : Screen("friendslist", R.string.friendsList, R.drawable.ic_fastfood)
    object FoodList : Screen("foodlist", R.string.foodList, R.drawable.ic_fitness_center)
}

@Composable
fun RecipeItem(recipesData: Recipe, onClick: () -> Unit) {
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
            val image = rememberCoilPainter(
                request = recipesData.image,
                fadeIn = true)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = recipesData.title,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            }
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
}