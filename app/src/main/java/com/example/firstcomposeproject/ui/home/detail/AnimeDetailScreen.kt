package com.example.firstcomposeproject.ui.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@Composable
fun AnimeDetailScreen(navController: NavHostController, id: String?) {

    var anime by remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        getAnime(id)?.let {
            anime = (it)
        }
        isLoading = false
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        if (isLoading) {

            CircularProgressIndicator(
                color = Color.Black,
                strokeWidth = 8.dp,
                modifier = Modifier
                    .size(50.dp)
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop,
                        model = if (anime.data.isEmpty()) {
                            "https://catherineasquithgallery.com/uploads/posts/2021-02/1613327507_26-p-krasivii-anime-fon-sinii-36.jpg"
                        } else {
                            anime.data[0].attributes.posterImage.original
                        },
                        contentDescription = "Image"
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x26000000),
                                        Color(0x80000000),
                                        Color(0xB3000000),
                                    )
                                )
                            )
                            .fillMaxWidth()
                            .height(60.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = if (anime.data.isEmpty() || anime.data[0].attributes.titles.en_jp.isEmpty()) {
                                "No Data"
                            } else {
                                anime.data[0].attributes.titles.en_jp
                            },
                            style = TextStyle(
                                color = Color.White,
                                fontStyle = FontStyle(800),
                                shadow = Shadow(color = Color.White, blurRadius = 1f),

                                )
                        )
                    }
                }

                Text(
                    text = "Description", style = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
                Text(
                    text = anime.data[0].attributes.description, style = TextStyle(
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
                Row {
                    Button(onClick = {

                    }) {
                        Text(text = "Add to favorite")
                    }
                    Button(onClick = {

                    }) {
                        Text(text = "Remove from favorite")
                    }
                }
            }
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