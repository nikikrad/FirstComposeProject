package com.example.firstcomposeproject.navigation.home

import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.firstcomposeproject.navigation.BottomBarScreen
import com.example.firstcomposeproject.navigation.Graph
import com.example.firstcomposeproject.ui.favorite.FavoriteScreen
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.home.detail.AnimeDetailScreen
import com.example.firstcomposeproject.ui.search.SearchScreen
import com.example.firstcomposeproject.ui.settings.SettingScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {

        }
        composable(route = BottomBarScreen.Search.route) {
           SearchScreen()
        }
        composable(route = BottomBarScreen.Favorite.route) {
           FavoriteScreen()
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen()
        }
        composable(Graph.DETAIL) { AnimeDetailScreen() }
    }
}


