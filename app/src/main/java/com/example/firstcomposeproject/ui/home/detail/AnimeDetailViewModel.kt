package com.example.firstcomposeproject.ui.home.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.ApiService
import com.example.firstcomposeproject.domain.response.AnimeResponse
import com.example.firstcomposeproject.domain.retrofit.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class AnimeDetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun getFirebaseAnime(id: String?) {
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database.reference
        if (auth.currentUser !== null) {
            database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
                .addOnSuccessListener {
                    var count = 0
                    if (it.children.toList().isEmpty()){
                        it.children.forEach { data ->
                            if (it.children.toList().size == count++) {
                                if (data.key.toString() == id) {
                                    data.child("")
                                }
                            } else {
                                _isLoading.value = false
                            }
                        }
                    }else{
                        _isLoading.value = false
                    }
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
            _isLoading.value = false
        }
        else{

        }
    }

    private val _animeResponse = MutableStateFlow(AnimeResponse(emptyList()))
    var animeResponse = _animeResponse.asStateFlow()

//    var anime = savedStateHandle.getStateFlow("animeResponse", AnimeResponse(emptyList())).value

    suspend fun getAnime(id: String?) = coroutineScope {
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        withContext(Dispatchers.IO) {
            _animeResponse.value = id?.toInt()?.let { retrofit.getAnimeById(idAnime = it).body() }!!
//            savedStateHandle["animeResponse"] = anime
        }
    }


}