package com.example.firstcomposeproject.ui.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AnimeDetailScreen() {
    
    Scaffold(modifier = Modifier
        .background(Color.Magenta)
        .fillMaxSize()) {
        Text(text = "Details")
    }
    
}