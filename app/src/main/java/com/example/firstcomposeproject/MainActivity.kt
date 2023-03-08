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
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {

            }
        }
    }
}

fun getAnime(){
    CoroutineScope(Dispatchers.IO).launch {
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        retrofit.getAnime(0)
    }
}


@Composable
fun itemAnime(name: String) {
    Card(
        modifier = Modifier.background(Color.DarkGray),
        shape = RoundedCornerShape(15.dp),
        elevation = 15.dp,

    ) {
        Column() {
            Box(
                modifier = Modifier.fillMaxSize(32.0f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://scontent-waw1-1.cdninstagram.com/v/t51.2885-15/154809551_267804544814655_5381401619428604965_n.jpg?stp=dst-jpg_e35&_nc_ht=scontent-waw1-1.cdninstagram.com&_nc_cat=103&_nc_ohc=7OdtBMNKnSIAX_txPVj&edm=ACWDqb8BAAAA&ccb=7-5&ig_cache_key=MjUxODk5MTk2MjkxNDU5ODc0OQ%3D%3D.2-ccb7-5&oh=00_AfDp5jQzVUFG29TST4QYZ39QimsxphyxZCGBt-bqxojZ-Q&oe=640C1CA5&_nc_sid=1527a3"),
                    contentDescription = "huh"
                )
            }

            Text(
                text = name)
        }
    }
}
