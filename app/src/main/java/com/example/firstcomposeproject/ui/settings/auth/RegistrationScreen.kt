package com.example.firstcomposeproject.ui.settings.auth

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.firstcomposeproject.domain.response.firebase.UserResponse
import com.example.firstcomposeproject.navigation.Graph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    var loginField by remember {
        mutableStateOf("")
    }
    var passwordField by remember {
        mutableStateOf("")
    }
    var repeatPasswordField by remember {
        mutableStateOf("")
    }
    var usersList: MutableList<UserResponse> = emptyList<UserResponse>().toMutableList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .size(50.dp)
                .padding(top = 15.dp, start = 10.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registration", style = TextStyle(
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
                singleLine = true,
                shape = RoundedCornerShape(11.dp)
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
                    imeAction = ImeAction.Next
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(11.dp),
                singleLine = true,
            )
            OutlinedTextField(
                value = repeatPasswordField,
                onValueChange = {
                    repeatPasswordField = it
                },
                placeholder = {
                    Text(text = "Repeat Password...")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(11.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
            )
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        if (loginField !== "" || passwordField !== "" || repeatPasswordField !== "") {
                            if (passwordField == repeatPasswordField) {
                                auth.createUserWithEmailAndPassword(
                                    loginField,
                                    passwordField
                                )
                                    .addOnSuccessListener {
                                        database.child("users").get()
                                            .addOnSuccessListener {
                                                it.children.forEach { data ->
                                                    usersList.add(
                                                        UserResponse(
                                                            data.child("userId").value.toString(),
                                                            data.child("email").value.toString()
                                                        )
                                                    )
                                                }
                                                database.child("users")
                                                    .child((usersList.last().userId.toInt() + 1).toString())
                                                    .setValue(
                                                        UserResponse(
                                                            (usersList.last().userId.toInt() + 1).toString(),
                                                            auth.currentUser?.email!!
                                                        )
                                                    )
                                            }.addOnFailureListener {
                                                Log.e("TAG", "RegistrationScreen: ${it.localizedMessage}", )
                                            }

                                        navController.navigate(
                                            Graph.PROFILE,
                                            navOptions = NavOptions.Builder()
                                                .setPopUpTo(Graph.SETTING, inclusive = true)
                                                .build()
                                        )
                                    }
                                    .addOnFailureListener {
//                                    Toast.makeText(navController.context, "Incorrect login or password", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                //different passwords
                            }
                        } else {
                            //incorrect enter data
                        }
                    }
                },
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text(text = "Enter", color = Color.Black)
            }
        }
    }
}