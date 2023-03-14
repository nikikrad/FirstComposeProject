package com.example.firstcomposeproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstcomposeproject.navigation.home.homeNavGraph
import com.example.firstcomposeproject.ui.favorite.FavoriteScreen
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.search.SearchScreen
import com.example.firstcomposeproject.ui.settings.SettingScreen

@Composable
fun RootNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        homeNavGraph(navController)
    }
}
//
//object Graph {
//    const val ROOT = "root_graph"
//    const val HOME = "home_graph"
//    const val SEARCH = "search_graph"
//    const val FAVORITE = "favorite_graph"
//    const val SETTING = "setting_graph"
//    const val DETAIL = "detail_graph"
//
//}
