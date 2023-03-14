package com.example.firstcomposeproject.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val anime = remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    LaunchedEffect(Unit) {
        getAnime()?.let {
            anime.value = (it)
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(anime.value.data){ item ->
            Card(modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
            ) {
                AsyncImage(
                    model = item.attributes.posterImage.large,
                    contentDescription = item.attributes.titles.en_jp
                    )
            }
        }
    }
}

suspend fun getAnime(): AnimeResponse? = coroutineScope {
    val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    var anime: AnimeResponse? = null
    withContext(Dispatchers.IO) {
        anime = retrofit.getAnime(Random.nextInt(2222, 3333)).body()!!
    }
    return@coroutineScope anime
}
