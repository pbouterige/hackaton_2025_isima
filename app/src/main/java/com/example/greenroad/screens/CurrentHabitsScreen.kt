package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentHabitsScreen(navController: NavController) {
    var currentTransport by remember { mutableStateOf("") }
    var currentFrequency by remember { mutableStateOf("") }
    var currentTravelTime by remember { mutableStateOf("") }
    var currentStops by remember { mutableStateOf("") }
    var currentIssues by remember { mutableStateOf("") }

    val transports = listOf("Voiture", "Vélo", "Bus", "Tram", "Marche", "Covoiturage", "Autre")
    val frequencies = listOf("Quotidien", "Hebdomadaire", "Mensuel", "Occasionnel")
    var expandedTransport by remember { mutableStateOf(false) }
    var expandedFrequency by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Vos habitudes actuelles",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expandedTransport,
            onExpandedChange = { expandedTransport = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = currentTransport,
                onValueChange = {},
                readOnly = true,
                label = { Text("Mode de transport actuel") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTransport) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedTransport,
                onDismissRequest = { expandedTransport = false }
            ) {
                transports.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            currentTransport = option
                            expandedTransport = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedFrequency,
            onExpandedChange = { expandedFrequency = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = currentFrequency,
                onValueChange = {},
                readOnly = true,
                label = { Text("Fréquence actuelle des déplacements") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFrequency) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedFrequency,
                onDismissRequest = { expandedFrequency = false }
            ) {
                frequencies.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            currentFrequency = option
                            expandedFrequency = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentTravelTime,
            onValueChange = { currentTravelTime = it },
            label = { Text("Temps de trajet actuel") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentStops,
            onValueChange = { currentStops = it },
            label = { Text("Arrêts actuels") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentIssues,
            onValueChange = { currentIssues = it },
            label = { Text("Problèmes rencontrés") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("future_needs") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Suivant")
        }
    }
} 