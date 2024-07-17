package com.example.littlelemon.navigation

import android.app.Activity.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.screens.*

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little Lemon", MODE_PRIVATE)

    NavHost(
        navController = navController,
        startDestination =
            if(sharedPreferences.getBoolean("isLoggedIn", false)) {
                Home.route
            } else Onboarding.route
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }

        composable(Home.route) {
            Home(navController)
        }

        composable(Profile.route) {
            Profile(navController)
        }
    }
}