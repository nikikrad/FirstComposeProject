package com.example.firstcomposeproject.domain

import com.example.firstcomposeproject.domain.response.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("anime?page[limit]=20")
    suspend fun getAnime(
        @Query("page[offset]") page: Int
    ): Response<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeById(
        @Query("filter[id]") idAnime: Int
    ): Response<AnimeResponse>
}