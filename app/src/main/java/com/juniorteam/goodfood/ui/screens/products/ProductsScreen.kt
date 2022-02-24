package com.juniorteam.goodfood.ui.screens.products

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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.Product
import com.juniorteam.goodfood.ui.widgets.SearchToolbar


@Composable
fun ProductsScreen(productsViewModel: ProductsViewModel) {
    val context = LocalContext.current
    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchToolbar(state = textState, onClick = {
            Toast.makeText(context, "side bar", Toast.LENGTH_SHORT).show()
        })
        ProductsList(state = textState, productViewModel = productsViewModel)
    }
}

@Composable
fun ProductsList(modifier: Modifier = Modifier, state:MutableState<TextFieldValue>,
                 productViewModel: ProductsViewModel) {
    val context = LocalContext.current

    if (state.value.text != "")
        productViewModel.setQuery(state.value.text)

    val productItems = productViewModel.getProductList().collectAsLazyPagingItems()

    LazyColumn {
        items(productItems) { item ->
            item?.let {
                ProductItem(productData = item, onClick = {
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                },
                )
            }
        }
        productItems.apply {
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
fun ProductItem(productData: Product, onClick: () -> Unit) {
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
                request = productData.image,
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
                    text = productData.title,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}
