package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class TransportLine(
    val name: String,
    val stops: List<String>,
    val frequency: String,
    val estimatedTime: String
)

@Composable
fun ProposalsScreen(navController: NavController) {
    // Données de démonstration
    val proposedLines = remember {
        listOf(
            TransportLine(
                "Ligne 1",
                listOf("Centre-ville", "Zone industrielle", "Centre commercial"),
                "Toutes les 30 minutes",
                "25 minutes"
            ),
            TransportLine(
                "Ligne 2",
                listOf("Gare", "Université", "Hôpital"),
                "Toutes les 20 minutes",
                "15 minutes"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Propositions de lignes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(proposedLines) { line ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = line.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Arrêts:",
                            style = MaterialTheme.typography.titleMedium
                        )
                        line.stops.forEach { stop ->
                            Text(
                                text = "• $stop",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Fréquence: ${line.frequency}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Temps estimé: ${line.estimatedTime}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("participation") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Donner mon avis")
        }
    }
} 