package com.example.firstcomposeproject.navigation.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstcomposeproject.navigation.BottomBarScreen
import com.example.firstcomposeproject.navigation.Graph
import com.example.firstcomposeproject.ui.favorite.FavoriteScreen
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.home.detail.AnimeDetailScreen
import com.example.firstcomposeproject.ui.search.SearchScreen
import com.example.firstcomposeproject.ui.settings.SettingScreen

@Composable
fun SearchNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.SEARCH,
        startDestination = BottomBarScreen.Search.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
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