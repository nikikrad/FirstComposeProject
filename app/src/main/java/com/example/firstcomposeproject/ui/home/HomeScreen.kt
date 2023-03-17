package com.example.firstcomposeproject.ui.home

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import com.example.firstcomposeproject.navigation.Graph
import com.example.firstcomposeproject.navigation.NavGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var anime by remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        getAnime()?.let {
            anime = (it)
        }
        delay(5000)
        isLoading = false
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(anime.data) { item ->
            ShimmerListItem(
                isLoading = isLoading,
                contentAfterLoading = {
                    Card(modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp)),
                        onClick = {
                            navController.navigate("detail/${item.id}")
                        }
                    ) {
                        AsyncImage(
                            model = item.attributes.posterImage.large,
                            contentDescription = item.attributes.titles.en_jp
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
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
