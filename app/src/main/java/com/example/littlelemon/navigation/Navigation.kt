package com.example.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.screens.*
import com.example.littlelemon.sharedPreferences

@Composable
fun Navigation(navController: NavHostController) {
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