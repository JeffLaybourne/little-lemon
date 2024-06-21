package com.example.littlelemon.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.littlelemon.sharedPreferences
import com.example.littlelemon.navigation.Onboarding

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the ProfileScreen\n" +
                    "isLoggedIn = ${sharedPreferences.getBoolean("isLoggedIn", false)}"
        )

        Button(onClick = {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            Toast.makeText(context, "Logging Out...",
                Toast.LENGTH_LONG).show()
            navController.navigate(Onboarding.route) }) {
            Text(text = "Logout")
        }
    }
}