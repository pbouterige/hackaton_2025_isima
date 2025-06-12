package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Trip(
    val departure: String,
    val arrival: String,
    val frequency: String,
    val time: String
)

@Composable
fun SummaryScreen(navController: NavController) {
    // Données de démonstration
    val trips = remember {
        listOf(
            Trip("Domicile", "Travail", "Quotidien", "8h00"),
            Trip("Travail", "Domicile", "Quotidien", "18h00"),
            Trip("Domicile", "Centre commercial", "Hebdomadaire", "14h00")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Synthèse de mes trajets",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(trips) { trip ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "${trip.departure} → ${trip.arrival}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Fréquence: ${trip.frequency}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Horaire: ${trip.time}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("proposals") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Analyser mes trajets")
        }
    }
} 