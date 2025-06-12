package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Stop(
    val name: String,
    val time: String,
    val type: String
)

data class ProposalDetails(
    val id: String,
    val name: String,
    val description: String,
    val status: String,
    val satisfactionRate: Float,
    val stops: List<Stop>,
    val frequency: String,
    val estimatedTime: String,
    val impact: String
)

@Composable
fun ProposalDetailsScreen(proposalId: String, navController: NavController) {
    // Données de démonstration
    val proposal = remember {
        ProposalDetails(
            id = proposalId,
            name = "Ligne Verte",
            description = "Liaison centre-ville - zone industrielle",
            status = "En cours de validation",
            satisfactionRate = 0.85f,
            stops = listOf(
                Stop("Centre-ville", "8h00", "Départ"),
                Stop("Zone résidentielle", "8h15", "Arrêt"),
                Stop("Zone industrielle", "8h30", "Terminus")
            ),
            frequency = "Toutes les 30 minutes",
            estimatedTime = "30 minutes",
            impact = "Réduction de 25% des émissions CO₂"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = proposal.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = proposal.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Horaires et arrêts",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                proposal.stops.forEach { stop ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stop.name,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stop.time,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Text(
                        text = stop.type,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Informations pratiques",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Fréquence: ${proposal.frequency}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Temps de trajet: ${proposal.estimatedTime}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Impact environnemental",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = proposal.impact,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("feedback") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Donner mon avis")
        }
    }
} 