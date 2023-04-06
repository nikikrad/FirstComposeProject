package com.example.firstcomposeproject.ui.favorite

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.firstcomposeproject.R
import com.example.firstcomposeproject.ui.favorite.tab_screens.ThrewScreen
import com.example.firstcomposeproject.ui.favorite.tab_screens.WatchedScreen
import com.example.firstcomposeproject.ui.favorite.tab_screens.WatchingScreen
import com.example.firstcomposeproject.ui.favorite.tab_screens.WillWatchScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun){

    object Watched : TabItem(R.drawable.watched, "Watched", { WatchedScreen() })
    object Watching : TabItem(R.drawable.watch, "Watching", { WatchingScreen() })
    object Threw : TabItem(R.drawable.resource_throw, "Threw", { ThrewScreen() })
    object WillWatch : TabItem(R.drawable.will_watch, "Will Watch", { WillWatchScreen() })

}