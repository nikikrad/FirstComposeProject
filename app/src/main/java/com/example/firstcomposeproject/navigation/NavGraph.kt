package com.example.firstcomposeproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstcomposeproject.ui.favorite.FavoriteScreen
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.home.detail.AnimeDetailScreen
import com.example.firstcomposeproject.ui.search.SearchScreen
import com.example.firstcomposeproject.ui.settings.SettingScreen
import com.example.firstcomposeproject.ui.settings.auth.AuthScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Graph.HOME){
        composable(Graph.HOME){
            HomeScreen(navController)
        }
        composable(Graph.SEARCH){
            SearchScreen(navController)
        }
        composable(Graph.FAVORITE){
            FavoriteScreen(navController)
        }
        composable(Graph.SETTING){
            SettingScreen(navController)
        }
        composable("detail/{ID}"){
            AnimeDetailScreen(navController , it.arguments?.getString("ID"))
        }
        composable(Graph.AUTH){
            AuthScreen()
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home"
    const val SEARCH = "search"
    const val FAVORITE = "favorite"
    const val SETTING = "setting"
    const val AUTH = "auth"
    const val DETAIL = "detail"

}