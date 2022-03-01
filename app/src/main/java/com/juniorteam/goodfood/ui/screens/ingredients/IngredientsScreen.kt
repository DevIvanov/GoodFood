package com.juniorteam.goodfood.ui.screens.ingredients

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.data.constants.ApiConstants.BASE_PATH_IMAGE
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.goodfood.R
import com.juniorteam.goodfood.ui.views.SearchToolbar


@Composable
fun IngredientsScreen(ingredientsViewModel: IngredientsViewModel) {
    val context = LocalContext.current
    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchToolbar(state = textState, onClick = {
            Toast.makeText(context, "side bar", Toast.LENGTH_SHORT).show()
        })
        IngredientsList(state = textState, ingredientsViewModel = ingredientsViewModel)
    }
}


@Composable
fun IngredientsList(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    ingredientsViewModel: IngredientsViewModel
) {

    ingredientsViewModel.getIngredientList()
    val context = LocalContext.current

    if (state.value.text != "")
        ingredientsViewModel.setQuery(state.value.text)

    val ingredientItems = ingredientsViewModel.ingredientList

    if (ingredientItems == null) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = "Data is empty!"
        )
    }else {
        LazyColumn {
            items(ingredientItems) { item ->
                item.let {
                    IngredientItem(
                        ingredientsData = item,
                        onClick = {
                            Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                        },
                    )
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
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.10f)
                    .fillMaxHeight()
                    .background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Icon(
                    painterResource(R.drawable.ic_favorite_border),
                    contentDescription = "Like icon"
                )
            }
        }
    }
}
