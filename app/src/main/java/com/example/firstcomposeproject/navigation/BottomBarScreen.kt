package com.example.firstcomposeproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Search: BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )
    object Favorite: BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )
    object Setting: BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )
}
