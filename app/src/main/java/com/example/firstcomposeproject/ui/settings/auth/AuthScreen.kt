package com.example.firstcomposeproject.ui.settings.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.ui.theme.Shapes
import kotlin.math.log

@Composable
fun AuthScreen() {
    var loginField by remember {
        mutableStateOf("")
    }
    var passwordField by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "accountIcon",
                modifier = Modifier.size(250.dp)
            )
            OutlinedTextField(
                value = loginField,
                onValueChange = {
                    loginField = it
                },

                placeholder = {
                    Text(text = "Login...")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    ),
                singleLine = true,
                )

            OutlinedTextField(
                value = passwordField,
                onValueChange = {
                    passwordField = it
                },
                placeholder = {
                    Text(text = "Password...")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                modifier = Modifier.border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                ),
                singleLine = true
            )


        }
    }
}