package com.example.firstcomposeproject.ui.favorite.tab_screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.response.DataResponse
import com.example.firstcomposeproject.domain.response.firebase.AnimeRequest
import com.example.firstcomposeproject.ui.home.shimmerListItemEffect
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun WatchedScreen() {
    val animeRequest = firebaseAnimeRequest.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        getAnime()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(animeRequest.value) {
           ItemCard(item = it)
        }
    }
}

private var firebaseAnimeRequest = MutableStateFlow(mutableListOf(AnimeRequest()))

private fun getAnime() {
    firebaseAnimeRequest.value.clear()
    val auth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    if (auth.currentUser !== null) {
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener {
                val list = mutableListOf(AnimeRequest())
                it.children.forEach { data ->
                    if (data.child("status").value == "Watched") {
                        list.add(
                            AnimeRequest(
                                id = data.child("id").value.toString(),
                                description = data.child("description").value.toString(),
                                title = data.child("title").value.toString(),
                                poster = data.child("poster").value.toString(),
                                status = data.child("status").value.toString()
                            )
                        )
                    }
                }
                firebaseAnimeRequest.value = list
                Log.e("TAG", "getAnime: ${firebaseAnimeRequest.value}")
            }
            .addOnFailureListener {

            }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemCard(item: AnimeRequest) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.Black),
        onClick = {
//            navController.navigate("detail/${item.id}")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .background(Color.LightGray)
        ) {
            AsyncImage(
                model = item.poster,
                contentDescription = item.title,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                if (item.title == null){
                    Text(text = "no data", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }else Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                Text(text = item.description, fontSize = 14.sp, maxLines = 2)
            }
        }
    }
}