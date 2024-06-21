package com.example.littlelemon.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.screens.Onboarding

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Onboarding.route)
    {
        composable(Onboarding.route) {
            Onboarding()
        }

    }
}