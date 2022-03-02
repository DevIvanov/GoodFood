package com.juniorteam.goodfood.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.juniorteam.goodfood.ui.navigation.SetupNavGraph
import com.juniorteam.goodfood.ui.screens.ingredient_details.IngredientDetailsViewModel
import com.juniorteam.goodfood.ui.screens.ingredients.IngredientsViewModel
import com.juniorteam.goodfood.ui.screens.product_details.ProductDetailsViewModel
import com.juniorteam.goodfood.ui.screens.products.ProductsViewModel
import com.juniorteam.goodfood.ui.screens.recipe_details.RecipeDetailsViewModel
import com.juniorteam.goodfood.ui.screens.recipes.RecipesViewModel
import com.juniorteam.goodfood.ui.theme.AnimatedSplashScreenDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recipesViewModel: RecipesViewModel by viewModels()
    private val ingredientsViewModel: IngredientsViewModel by viewModels()
    private val productsViewModel: ProductsViewModel by viewModels()

    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()
    private val ingredientDetailsViewModel: IngredientDetailsViewModel by viewModels()
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AnimatedSplashScreenDemoTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    recipesViewModel = recipesViewModel,
                    ingredientsViewModel = ingredientsViewModel,
                    productsViewModel = productsViewModel,
                    recipeDetailsViewModel = recipeDetailsViewModel,
                    ingredientDetailsViewModel = ingredientDetailsViewModel,
                    productDetailsViewModel = productDetailsViewModel
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {

    }
}