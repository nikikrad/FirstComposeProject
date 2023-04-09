package com.example.firstcomposeproject.ui.home.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.response.firebase.AnimeRequest
import com.example.firstcomposeproject.ui.home.shimmerListItemEffect
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import com.google.android.gms.common.wrappers.Wrappers.packageManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AnimeDetailScreen(navController: NavHostController, id: String?) {
    FirstComposeProjectTheme() {

        val viewModel = viewModel<AnimeDetailViewModel>()
        LaunchedEffect(key1 = true) {
            viewModel.getAnime(id)
            viewModel.getFirebaseAnime(id)
        }
        val context = LocalContext.current
        val animeResponse by viewModel.animeResponse.collectAsStateWithLifecycle()
        val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
        val animeStatus by viewModel.animeStatus.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .scrollable(ScrollableState { 1.0f }, orientation = Orientation.Vertical)
        ) {
            if (animeResponse.data.isEmpty()) {
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
                            model = if (animeResponse.data.isEmpty()) {
                                "https://catherineasquithgallery.com/uploads/posts/2021-02/1613327507_26-p-krasivii-anime-fon-sinii-36.jpg"
                            } else {
                                animeResponse.data[0].attributes.posterImage.original
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
                                text = if (animeResponse.data.isEmpty() || animeResponse.data[0].attributes.titles.en_jp.isEmpty()) {
                                    "No Data"
                                } else {
                                    animeResponse.data[0].attributes.titles.en_jp
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
                        text = animeResponse.data[0].attributes.description, style = TextStyle(
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                    ShimmerButtonGroup(
                        isLoading = isLoading,
                        contentAfterLoading = {
                            CustomRadioGroup(animeResponse, viewModel, animeStatus)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            val intent = Intent()
                            intent.action = Intent.ACTION_SEND
                            intent.putExtra(
                                Intent.EXTRA_TEXT,
                                if (animeResponse.data[0].attributes.youtubeVideo == null || animeResponse.data[0].attributes.youtubeVideo == ""){
                                    "${animeResponse.data[0].attributes.titles.en_jp}\n\n${animeResponse.data[0].attributes.posterImage.original}"
                                }else{
                                    "${animeResponse.data[0].attributes.titles.en_jp}\n\nhttps://www.youtube.com/watch?v=${animeResponse.data[0].attributes.youtubeVideo}"
                                }
                            )
                            intent.type = "text/plain"
                            if (intent.resolveActivity(context.packageManager) !== null) {
                                context.startActivity(intent)
                            }
                        },
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.CenterHorizontally),
                    ) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "icon", modifier = Modifier.size(24.dp), tint = Color.Black)
                        Text(text = "Share", color = Color.Black)
                    }
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomRadioGroup(anime: AnimeResponse, viewModel: AnimeDetailViewModel, animeStatus: String) {
    val options = listOf(
        "Haven't watched",
        "Watched",
        "Watching",
        "Threw",
        "Will watch",
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(
                        all = 4.dp,
                    ),
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = if (text == animeStatus) {
                            Color.White
                        } else {
                            Color.Black
                        },
                        fontSize = 10.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )
                        .clickable {
                            val auth = FirebaseAuth.getInstance()
                            val database = Firebase.database.reference
                            if (text == "Haven't watched") {
                                database
                                    .child(
                                        auth.currentUser?.email
                                            .toString()
                                            .substringBefore("@")
                                    )
                                    .get()
                                    .addOnSuccessListener {
                                        it.child(anime.data[0].id.toString()).ref.removeValue()
                                    }
                            } else {
                                database
                                    .child(
                                        auth.currentUser?.email
                                            .toString()
                                            .substringBefore("@")
                                    )
                                    .child(anime.data[0].id.toString())
                                    .setValue(
                                        AnimeRequest(
                                            anime.data[0].id.toString(),
                                            anime.data[0].attributes.description,
                                            anime.data[0].attributes.titles.en_jp,
                                            anime.data[0].attributes.posterImage.original,
                                            text
                                        )
                                    )
                            }
                            viewModel.onSelectionChange(text)
                        }
                        .background(
                            if (text == animeStatus) {
                                Color.Black
                            } else {
                                Color.LightGray
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 6.dp,
                        ),
                )
            }
        }
    }
}

@Composable
fun ShimmerButtonGroup(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    all = 44.dp,
                )
                .clip(
                    shape = RoundedCornerShape(
                        size = 12.dp,
                    ),
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerListItemEffect()
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp,
                        ),
                    )
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerListItemEffect()
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp,
                        ),
                    )

            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerListItemEffect()
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp,
                        ),
                    )
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerListItemEffect()
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp,
                        ),
                    )
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .shimmerListItemEffect()
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp,
                        ),
                    )
            )
        }
    } else {
        contentAfterLoading()
    }
}