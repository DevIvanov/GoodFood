package com.juniorteam.goodfood.ui.screens.recipes

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.Recipe
import com.juniorteam.goodfood.ui.navigation.nav_objects.Screen
import com.juniorteam.goodfood.ui.screens.ingredients.SearchToolbar


@Composable
fun RecipesScreen(recipesViewModel: RecipesViewModel,externalNavGraph: NavHostController) {
    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchToolbar(state = textState)
        RecipesList(
            state = textState,
            recipesViewModel = recipesViewModel,
            externalNavGraph = externalNavGraph
        )
    }
}

@Composable
fun RecipesList(modifier: Modifier = Modifier, state: MutableState<TextFieldValue>,
                recipesViewModel: RecipesViewModel,externalNavGraph: NavHostController
) {
    val context = LocalContext.current

    if (state.value.text != "")
        recipesViewModel.setQuery(state.value.text)

    val recipesItems = recipesViewModel.getRecipesList().collectAsLazyPagingItems()

    LazyColumn {
        items(recipesItems) { item ->
            item?.let {
                RecipeItem(recipesData = item, onClick = {
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                    externalNavGraph.navigate(Screen.RecipeDetails.route)
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
fun RecipeItem(recipesData: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val image = rememberCoilPainter(
                request = recipesData.image,
                fadeIn = true
            )
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
}