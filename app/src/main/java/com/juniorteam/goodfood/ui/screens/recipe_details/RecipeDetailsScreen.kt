package com.juniorteam.goodfood.ui.screens.recipe_details

import android.content.Context
import android.media.Image
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.juniorteam.goodfood.R
import com.juniorteam.goodfood.ui.theme.Avocado
import com.juniorteam.goodfood.ui.theme.Cilantro
import com.juniorteam.goodfood.ui.theme.cToast


@Composable
fun RecipeDetailsScreen(navController: NavController) {

    val context = LocalContext.current

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
            .height(50.dp)
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
            text = "Title",
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
        Image(
            painterResource(R.drawable.image_test),
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
            text = "45",
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
            Image(
                painter = painterResource(R.drawable.ic_done_icon),
                contentDescription = "gluten icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Gluten free",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.ic_close_icon),
                contentDescription = "vegan icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Vegan",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.ic_done_icon),
                contentDescription = "vegetarian icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
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
