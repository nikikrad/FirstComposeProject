package com.example.firstcomposeproject.ui.settings.profile

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.firstcomposeproject.navigation.Graph
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "accountIcon",
                modifier = Modifier.size(150.dp)
            )
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            ) {
                Text(text = "Edit profile", color = Color.White)
            }
            Button(
                onClick = {
                    auth.signOut()
                    navController.navigate(Graph.AUTH,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(Graph.SETTING, inclusive = true)
                            .build())
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),

                ) {
                Text(text = "Log out", color = Color.White)
            }
        }
    }
}