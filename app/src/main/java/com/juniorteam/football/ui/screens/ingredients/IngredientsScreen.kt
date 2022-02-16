package com.juniorteam.football.ui.screens.ingredients

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientsScreen {
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
                Log.e("TAG", item!!.toString())
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
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val image = rememberCoilPainter(
                    request = ingredientsData.image,
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
}