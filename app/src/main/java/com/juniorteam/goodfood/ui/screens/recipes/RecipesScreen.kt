package com.juniorteam.goodfood.ui.screens.recipes

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.Recipe
import com.juniorteam.goodfood.ui.navigation.nav_objects.Screen
import com.juniorteam.goodfood.ui.navigation.navigate_extensions.navigate
import com.juniorteam.goodfood.ui.views.SearchToolbar
import kotlinx.coroutines.launch


@Composable
fun RecipesScreen(recipesViewModel: RecipesViewModel, externalNavGraph: NavHostController) {
    val context = LocalContext.current

    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchToolbar(state = textState, onClick = {
            Toast.makeText(context, "side bar", Toast.LENGTH_SHORT).show()
        })
        RecipesList(
            state = textState,
            recipesViewModel = recipesViewModel,
            externalNavGraph = externalNavGraph
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RecipesList(
    modifier: Modifier = Modifier, state: MutableState<TextFieldValue>,
    recipesViewModel: RecipesViewModel, externalNavGraph: NavHostController
) {
    Log.e("RecipesList", "RecipesList was called!")
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }

    if (recipesViewModel.headerLeftQuota != ""){
        scope.launch {
            snackbarHostState.value.showSnackbar("Hello")
        }
    }
//    makeText(context, "Left Quota = ${recipesViewModel.headerLeftQuota} for this key!", Toast.LENGTH_SHORT).show()

    if (state.value.text != "") { // TODO when first launch application
        recipesViewModel.getRecipesList()
        recipesViewModel.setQuery(state.value.text)
        Log.e("RecipesList", "Network response!")
    } else {
        recipesViewModel.readAllData()
        Log.e("RecipesList", "Database response!")
        Toast.makeText(context, "Data from database!", Toast.LENGTH_SHORT).show()
    }

    val recipesItems = recipesViewModel.recipesList

    if (recipesItems == null) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = "Data is empty!"
        )
    } else {
        LazyColumn {
            items(recipesItems) { item ->
                item.let {
                    RecipeItem(recipesData = item, onClick = {
                        val argument = item.id.toString()
                        externalNavGraph.navigate(
                            Screen.RecipeDetails.route,
                            bundleOf("id" to argument)
                        )
                    })
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
                contentScale = ContentScale.Inside
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = recipesData.title,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 18.sp
                )
            }
        }
    }
}

//private fun setRecyclerViewScrollListener() {
//    binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//            val totalItemCount = recyclerView.layoutManager!!.itemCount
//            if (totalItemCount == lastVisibleItemPosition + 1) {
//                viewModel.getPhotos()
//            }
//        }
//    })
//}

