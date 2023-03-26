package com.example.firstcomposeproject.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firstcomposeproject.navigation.Graph
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(start = 2.dp, end = 2.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        SettingButton("Profile", navController)
        SettingButton("Themes", navController)
        SettingButton("Language", navController)
    }
}

@Composable
fun SettingButton(name: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(width = 1.dp, color = Color.White)
            .height(60.dp)
    ) {
        Button(
            onClick = {
                when (name) {
                    "Profile" -> if (checkLoggedInState()) navController.navigate(Graph.PROFILE)
                    else navController.navigate(Graph.AUTH)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .background(Color.LightGray)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var icon: ImageVector = Icons.Default.Done
                when (name) {
                    "Authentication" -> icon = Icons.Default.Email
                    "Themes" -> icon = Icons.Default.Create
                    "Language" -> icon = Icons.Default.AccountBox
                }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = name,
                    style = TextStyle(color = Color.Black)
                )
            }
        }
    }

}

fun checkLoggedInState(): Boolean {
    val auth = FirebaseAuth.getInstance()
    return auth.currentUser !== null
}