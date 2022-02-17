package com.juniorteam.football.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.juniorteam.football.ui.screens.bottom_nav_bar.ingredients.IngredientsScreen
import com.juniorteam.football.ui.screens.bottom_nav_bar.ingredients.IngredientsViewModel
import com.juniorteam.football.ui.screens.bottom_nav_bar.navigation_bottom_bar.NavigationBottomBar
import com.juniorteam.football.ui.screens.bottom_nav_bar.products.ProductsScreen
import com.juniorteam.football.ui.screens.bottom_nav_bar.products.ProductsViewModel
import com.juniorteam.football.ui.screens.bottom_nav_bar.recipes.RecipesScreen
import com.juniorteam.football.ui.screens.bottom_nav_bar.recipes.RecipesViewModel
import com.juniorteam.football.ui.screens.navigation.SetupNavGraph
import com.juniorteam.football.ui.theme.AnimatedSplashScreenDemoTheme
import com.juniorteam.football.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recipesViewModel: RecipesViewModel by viewModels()
    private val ingredientsViewModel: IngredientsViewModel by viewModels()
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AnimatedSplashScreenDemoTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    recipesList = recipesViewModel.recipesList,
                    ingredientList = ingredientsViewModel.ingredientList,
                    productsList = productsViewModel.productList,
                    context = this
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {

    }
}

