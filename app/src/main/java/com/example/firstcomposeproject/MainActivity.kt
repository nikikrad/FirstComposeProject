package com.example.firstcomposeproject

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import com.example.firstcomposeproject.navigation.RootNavGraph
import com.example.firstcomposeproject.ui.home.HomeScreen
import com.example.firstcomposeproject.ui.main.MainScreen
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigation(
                modifier = Modifier.fillMaxSize()
            ) {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}