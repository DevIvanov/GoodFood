package com.juniorteam.goodfood.ui.screens.product_details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.juniorteam.domain.model.ProductDetails
import com.juniorteam.goodfood.R
import com.juniorteam.goodfood.ui.theme.Avocado
import com.juniorteam.goodfood.ui.theme.Cilantro

class ProductDetailsScreen {
    private val tag = ProductDetailsScreen::class.java.simpleName
    private lateinit var data: ProductDetails

    @Composable
    fun ProductDetailsScreen(productDetailsViewModel: ProductDetailsViewModel, navController: NavController) {
        val context = LocalContext.current

        try {
            navController.previousBackStackEntry?.arguments?.getString("id")
                ?.let { productDetailsViewModel.getProductById(it) }
        }catch (e: Exception){
            Log.e(tag, e.message.toString())
        }

        data = productDetailsViewModel.product.value!!

        Column {
            Toolbar {
                navController.navigateUp()
            }

            LazyColumn(
                content = {
                    item {
                        RecipeMainImage()
                    }
                    item {
                        RecipeReadyTime()
                    }
                    item {
                        RecipeCharacteristic()
                    }
                    item {
                        RecipeSourceLink {
                            Toast.makeText(context, "Link open", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Composable
    fun Toolbar(onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(color = Cilantro)
        )
        {
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.2f)
                    .fillMaxHeight()
                    .background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back icon")
            }
            Text(
                text = "data.title",
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .align(alignment = Alignment.CenterVertically),
                fontSize = 20.sp
            )
        }
    }

    @Composable
    fun RecipeMainImage() {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val image = rememberCoilPainter(
                request = "data.image",
                fadeIn = true,
                previewPlaceholder = R.drawable.dish,
                fadeInDurationMs = 500
            )
            Image(
                painter = image,
                contentDescription = "test image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }

    @Composable
    fun RecipeReadyTime() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_time_icon),
                contentDescription = "time icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "data.readyInMinutes".toString(),
                fontSize = 30.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
            Text(
                text = "m",
                fontSize = 30.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
    }

    @Composable
    fun RecipeCharacteristic() {
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
//                Image(
//                    painter = getImage(data.glutenFree),
//                    contentDescription = "gluten icon",
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier
//                        .size(34.dp)
//                        .clip(CircleShape)
//                        .padding(start = 8.dp)
//                        .align(Alignment.CenterVertically)
//                )
                Text(
                    text = "Gluten free",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 10.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
//                Image(
//                    painter = getImage(data.vegan),
//                    contentDescription = "vegan icon",
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier
//                        .size(34.dp)
//                        .clip(CircleShape)
//                        .padding(start = 8.dp)
//                        .align(Alignment.CenterVertically)
//                )
                Text(
                    text = "Vegan",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 10.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
//                Image(
//                    painter = getImage(data.vegetarian),
//                    contentDescription = "vegetarian icon",
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier
//                        .size(34.dp)
//                        .clip(CircleShape)
//                        .padding(start = 8.dp)
//                        .align(Alignment.CenterVertically)
//                )
                Text(
                    text = "Vegetarian",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 10.dp)
                )
            }
        }
    }

    @Composable
    fun RecipeSourceLink(onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Avocado)
        ) {
//        Text(
//            text = "Open full recipe",
//            modifier = Modifier
//                .fillMaxWidth(fraction = 0.7f)
//                .align(alignment = Alignment.CenterVertically),
//            fontSize = 26.sp
//        )
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                elevation = null
            ) {
                Text(
                    text = "Open full recipe",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(top = 6.dp, bottom = 6.dp),
                    fontSize = 26.sp
                )
            }
        }
    }

//    @Composable
//    fun getImage(isTrue: Boolean): Painter {
//        return if (data.glutenFree)
//            painterResource(id = R.drawable.ic_done_icon)
//        else
//            painterResource(id = R.drawable.ic_close_icon)
//    }
}

