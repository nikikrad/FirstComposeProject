package com.example.firstcomposeproject.ui.home.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle.Alignment.Companion.Bottom
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import com.example.firstcomposeproject.ui.theme.HalfDarkColor
import com.example.firstcomposeproject.ui.theme.Purple200
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.random.Random

@Composable
fun AnimeDetailScreen(navController: NavHostController, id: String?) {

    var anime by remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    LaunchedEffect(key1 = true) {
        getAnime(id)?.let {
            anime = (it)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                model = if (anime.data.isEmpty()){
                    "https://catherineasquithgallery.com/uploads/posts/2021-02/1613327507_26-p-krasivii-anime-fon-sinii-36.jpg"
                } else{
                    anime.data[0].attributes.posterImage.original
                },
                contentDescription = "Image"
            )


            Box(
                modifier = Modifier
                    .background(HalfDarkColor)
                    .fillMaxWidth()
                    .height(30.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

suspend fun getAnime(id: String?): AnimeResponse? = coroutineScope {
    val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    var anime: AnimeResponse? = null
    withContext(Dispatchers.IO) {
        anime = id?.toInt()?.let { retrofit.getAnimeById(idAnime = it).body() }!!
    }
    return@coroutineScope anime
}