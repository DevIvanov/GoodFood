package com.juniorteam.goodfood.ui.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.juniorteam.goodfood.R
import com.juniorteam.goodfood.ui.theme.Avocado
import com.juniorteam.goodfood.ui.theme.Cilantro

@Composable
fun SearchToolbar(state: MutableState<TextFieldValue>, onClick: () -> Unit) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(color = Cilantro),
        elevation = 8.dp,
        color = Avocado
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.14f)
                    .fillMaxHeight()
                    .background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Icon(painterResource(R.drawable.ic_dehaze), contentDescription = "Menu icon")
            }
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
                ),
            )
        }
    }
}