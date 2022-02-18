package com.juniorteam.goodfood.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.ingredients.IngredientsViewModel
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.products.ProductsViewModel
import com.juniorteam.goodfood.ui.screens.bottom_nav_bar.recipes.RecipesViewModel
import com.juniorteam.goodfood.ui.screens.navigation.SetupNavGraph
import com.juniorteam.goodfood.ui.theme.AnimatedSplashScreenDemoTheme
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
                    ingredientsViewModel = ingredientsViewModel,
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

