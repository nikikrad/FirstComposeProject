package com.example.firstcomposeproject.ui.search

import android.util.Log
import android.widget.SearchView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.firstcomposeproject.domain.response.DataResponse
import com.example.firstcomposeproject.navigation.BottomBarScreen
import com.example.firstcomposeproject.ui.theme.Shapes
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    var anime by remember {
        mutableStateOf(AnimeResponse(emptyList()))
    }
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Surface(
            elevation = 20.dp,
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(end = 8.dp)
                )
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        GlobalScope.launch(Dispatchers.IO) {
                            anime = getAnime(searchText)
                        }
                    },
                    placeholder = {
                        Text(text = "Search...")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        textColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                            GlobalScope.launch(Dispatchers.IO) {
                                anime = getAnime(searchText)
                            }
                        }
                    )
                )
            }
        }
        LazyColumn {
            items(anime.data){ item ->
                ItemCard(item = item, navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemCard(item: DataResponse, navController: NavHostController) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.Black),
        onClick = {
            navController.navigate("detail/${item.id}")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .background(Color.LightGray)
        ) {
            AsyncImage(
                model = item.attributes.posterImage.original,
                contentDescription = item.attributes.titles.en_jp,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                if (item.attributes.titles.en_jp == null){
                    Text(text = "no data", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }else Text(text = item.attributes.titles.en_jp, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                Text(text = item.attributes.description, fontSize = 14.sp, maxLines = 2)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(Icons.Default.Star, contentDescription = "Rating")
                    Text(text = item.attributes.averageRating.toString(), fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
    }
}

suspend fun getAnime(name: String): AnimeResponse = coroutineScope {
    val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    var anime: AnimeResponse? = null
    withContext(Dispatchers.IO) {
        anime = retrofit.getAnimeByName(name).body()!!
    }
    return@coroutineScope anime!!
}
