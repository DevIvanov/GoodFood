package com.juniorteam.football.ui.screens.bottom_nav_bar.ingredients

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.data.constants.ApiConstants.BASE_PATH_IMAGE
import com.juniorteam.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

@Composable
fun IngredientsScreen(viewModel: IngredientsViewModel, context: Context) {
    Column {
        SearchToolbar(ingredientsViewModel = viewModel)
        IngredientsList(ingredientsList = viewModel.ingredientList, context = context)
    }
}

@Composable
fun SearchToolbar(ingredientsViewModel: IngredientsViewModel) {
    Surface(modifier = Modifier
        .fillMaxWidth(),
//        elevation = 8.dp
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),//(.9f)
//                    .padding(8.dp),
                value = "", //ingredientsViewModel.query.value!!,
                onValueChange = {ingredientsViewModel.setQuery(it)},
                label = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(Icons.Filled.Search, "")
                },
//                onImeActionPerformed = { action, softKeyboardController ->
//                    if (action == ImeAction.Done) {
//                        ingredientsViewModel.setQuery(query = ingredientsViewModel.query.value!!)
//                        softKeyboardController?.hideSoftwareKeyboard()
//                    }
//                },
                textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
//                backgroundColor = MaterialTheme.colors.surface
            )
        }

    }
}

    @Composable
    fun IngredientsList(modifier: Modifier = Modifier, ingredientsList: Flow<PagingData<Ingredient>>, context: Context) {
        val ingredientItems = ingredientsList.collectAsLazyPagingItems()

        LazyColumn {
            items(ingredientItems) { item ->
                item?.let {
                    IngredientItem(ingredientsData = item, onClick = {
                        Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                    },
                    )
                }
            }
            ingredientItems.apply {
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
    fun IngredientItem(ingredientsData: Ingredient, onClick: () -> Unit) {
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
                    request = BASE_PATH_IMAGE + ingredientsData.image,
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
                        text = ingredientsData.name,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface,
                    )
                }
            }
        }
    }
