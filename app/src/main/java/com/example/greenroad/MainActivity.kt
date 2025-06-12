package com.example.greenroad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenroad.ui.theme.GreenRoadTheme
import com.example.greenroad.screens.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenRoadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showSplash by remember { mutableStateOf(true) }
                    LaunchedEffect(Unit) {
                        delay(1200)
                        showSplash = false
                    }
                    if (showSplash) {
                        SplashScreen()
                    } else {
                        MobilinkApp()
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "SplashScreen",
            modifier = Modifier.size(250.dp)
        )
    }
}

@Composable
fun MobilinkApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        // Partie 1 : Collecte des données
        composable("welcome") { WelcomeScreen(navController) }
        composable("current_habits") { CurrentHabitsScreen(navController) }
        composable("future_needs") { FutureNeedsScreen(navController) }
        composable("thank_you") { ThankYouScreen(navController) }
        
        // Partie 2 : Consultation des propositions
        composable("proposals_overview") { ProposalsOverviewScreen(navController) }
        composable("proposal_details/{proposalId}") { backStackEntry ->
            ProposalDetailsScreen(
                proposalId = backStackEntry.arguments?.getString("proposalId") ?: "",
                navController = navController
            )
        }
        composable("feedback") { FeedbackScreen(navController) }
    }
}