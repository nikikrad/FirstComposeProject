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
import com.example.firstcomposeproject.ui.settings.auth.RegistrationScreen
import com.example.firstcomposeproject.ui.settings.profile.ProfileScreen

@Composable
fun SettingNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Graph.SETTING) {
        composable(Graph.HOME) {
            HomeScreen(navController)
        }
        composable(Graph.SEARCH) {
            SearchScreen(navController)
        }
        composable(Graph.FAVORITE) {
            FavoriteScreen(navController)
        }
        composable(Graph.SETTING) {
            SettingScreen(navController)
        }
        composable(Graph.PROFILE) {
            ProfileScreen(navController)
        }
        composable(Graph.AUTH) {
            AuthScreen(navController)
        }
        composable(Graph.REGISTRATION) {
            RegistrationScreen(navController)
        }
    }
}