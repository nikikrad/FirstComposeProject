package com.example.firstcomposeproject.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val appModule = module {
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    fun provideFirebaseDatabase(): DatabaseReference {
        return Firebase.database.reference
    }
}