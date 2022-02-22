package com.juniorteam.goodfood.ui.screens.recipe_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun RecipeDetailsScreen() {

    val context = LocalContext.current

    LazyColumn(
        content = {
            item {
                Toolbar {
                    Toast.makeText(context, "BackButtonClick", Toast.LENGTH_LONG).show()

                }
            }
            item {
                TextTest()
            }
        },
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun Toolbar(onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.Cyan)
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
fun TextTest() {
    Text(text = "lol",
        Modifier
            .height(2000.dp)
            .background(Color.Blue)
    )
}
