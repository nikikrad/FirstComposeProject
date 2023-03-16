package com.example.firstcomposeproject.ui.home.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.random.Random

@Composable
fun AnimeDetailScreen(navController: NavHostController, id: String?) {

    val anime = remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    LaunchedEffect(Unit) {
//        getAnime(id)?.let {
//            anime.value = (it)
//        }
    }
    
    Box(modifier = Modifier.background(Color.Blue)){
        Card(modifier = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
        ) {
            val q = anime.value
            val t = id


//            AsyncImage(
//                model = anime.value.data[0].attributes.posterImage.large,
//                contentDescription = anime.value.data[0].attributes.titles.en_jp
//            )
        }
    }
    
}

suspend fun getAnime(id: String?): AnimeResponse? = coroutineScope {
    val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    var anime: AnimeResponse? = null
    val q = id
    withContext(Dispatchers.IO) {
        anime = id?.toInt()?.let { retrofit.getAnimeById(idAnime = it).body() }!!
    }
    return@coroutineScope anime
}