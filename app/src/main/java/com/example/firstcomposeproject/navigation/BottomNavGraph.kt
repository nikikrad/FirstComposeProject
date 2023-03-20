package com.example.firstcomposeproject.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeproject.ui.favorite.FavoriteScreen
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.search.SearchScreen
import com.example.firstcomposeproject.ui.settings.SettingScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val listItems = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Favorite,
        BottomBarScreen.Setting
    )
//    var isVisible = remember {
//        mutableStateOf(true)
//    }
//    AnimatedVisibility(
//        visible = isVisible.value
//    ) {
        BottomNavigation(
            backgroundColor = Color.DarkGray,
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
//            isVisible.value =
//                currentRoute == Graph.HOME || currentRoute == Graph.SEARCH || currentRoute == Graph.FAVORITE || currentRoute == Graph.SETTING

            listItems.forEach {
                BottomNavigationItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route)
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.route
                        )
                    },
                    label = {
                        Text(
                            text = it.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black
                )
            }
        }
//    }
}