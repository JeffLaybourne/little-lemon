package com.example.littlelemon.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.littlelemon.sharedPreferences
import com.example.littlelemon.navigation.Profile

@Composable
fun Home(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the HomeScreen\n" +
                    "isLoggedIn = ${sharedPreferences.getBoolean("isLoggedIn", false)}"
        )

        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Go To Profile Page")
        }
    }
}