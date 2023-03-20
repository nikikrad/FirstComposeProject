package com.example.firstcomposeproject.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.firstcomposeproject.navigation.Graph

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Box(modifier = Modifier.background(Color.Yellow)
        .fillMaxSize())
}