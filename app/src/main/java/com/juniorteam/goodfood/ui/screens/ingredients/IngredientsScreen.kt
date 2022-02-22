package com.juniorteam.goodfood.ui.screens.ingredients

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.data.constants.ApiConstants.BASE_PATH_IMAGE
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_INGREDIENT
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.goodfood.R
import com.juniorteam.goodfood.ui.theme.Avocado
import com.juniorteam.goodfood.ui.theme.Cilantro
import com.juniorteam.goodfood.ui.theme.SmokedSalmon


@Composable
fun IngredientsScreen(ingredientsViewModel: IngredientsViewModel) {
    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchToolbar(state = textState)
        IngredientsList(state = textState, ingredientsViewModel = ingredientsViewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchToolbar(state = textState)
}

@Composable
fun SearchToolbar(state: MutableState<TextFieldValue>) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 8.dp,
        color = Avocado
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textState.value,
                onValueChange = { value -> textState.value = value },
                label = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                        if (textState.value.text != "") {
                            Toast.makeText(context, "Search ${textState.value.text}", Toast.LENGTH_SHORT).show()
                            state.value = textState.value
                        }else
                            Toast.makeText(context, "Enter text!", Toast.LENGTH_SHORT).show()
                    }
                ),
                leadingIcon = {
                    Icon(Icons.Filled.Search, "")
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}

@Composable
fun IngredientsList(modifier: Modifier = Modifier,
                    state: MutableState<TextFieldValue>,
                    ingredientsViewModel: IngredientsViewModel) {

    val context = LocalContext.current

    if (state.value.text != "")
        ingredientsViewModel.setQuery(state.value.text)

    val ingredientItems = ingredientsViewModel.getIngredientList().collectAsLazyPagingItems()
    
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
