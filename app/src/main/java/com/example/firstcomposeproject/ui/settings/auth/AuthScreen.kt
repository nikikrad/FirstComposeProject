package com.example.firstcomposeproject.ui.settings.auth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstcomposeproject.navigation.Graph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthScreen(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    var loginField by remember {
        mutableStateOf("")
    }
    var passwordField by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.size(50.dp).padding(top = 20.dp, start = 20.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(25.dp),
                tint = Color.Black
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in", style = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                )
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "accountIcon",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 50.dp)
            )
            OutlinedTextField(
                value = loginField,
                onValueChange = {
                    loginField = it
                },

                placeholder = {
                    Text(text = "Login...")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    ),
                singleLine = true,
            )

            OutlinedTextField(
                value = passwordField,
                onValueChange = {
                    passwordField = it
                },
                placeholder = {
                    Text(text = "Password...")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                modifier = Modifier.border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                ),
                singleLine = true
            )
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        if (loginField !== "" || passwordField !== "") {
                            val q = loginField
                            val t = q
                            auth.signInWithEmailAndPassword(
                                loginField,
                                passwordField
                            )
                                .addOnSuccessListener {
                                    navController.clearBackStack(Graph.SETTING)
                                }
                                .addOnFailureListener {
//                                    Toast.makeText(navController.context, "Incorrect login or password", Toast.LENGTH_SHORT).show()
                                }
                        } else {
//                            Toast.makeText(navController.context, "Fill in all the fields", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text(text = "Enter", color = Color.Black)
            }
            Button(
                onClick = {
                    navController.navigate(Graph.REGISTRATION)
                },
                modifier = Modifier.padding(top = 60.dp)
            ) {
                Text(text = "Registration")
            }
        }
    }
}
