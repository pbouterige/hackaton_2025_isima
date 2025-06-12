package com.example.greenroad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenroad.screens.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("current_habits") {
            CurrentHabitsScreen(navController)
        }
        composable("future_needs") {
            FutureNeedsScreen(navController)
        }
        composable("thank_you") {
            ThankYouScreen(navController)
        }
        composable("proposals_overview") {
            ProposalsOverviewScreen(navController)
        }
        composable("lines_map") {
            LinesMapScreen(navController)
        }
    }
} 