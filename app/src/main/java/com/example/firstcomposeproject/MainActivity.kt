package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeproject.navigation.BottomBarScreen
import com.example.firstcomposeproject.navigation.BottomNavGraph
import com.example.firstcomposeproject.navigation.Graph
import com.example.firstcomposeproject.navigation.RootNavGraph
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.main.MainScreen
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          FirstComposeProjectTheme {
              MainScreen()
          }
        }
    }
}
