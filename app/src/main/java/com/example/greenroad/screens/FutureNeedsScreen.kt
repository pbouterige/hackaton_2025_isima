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
fun FutureNeedsScreen(navController: NavController) {
    var preferredTransport by remember { mutableStateOf("") }
    var desiredFrequency by remember { mutableStateOf("") }
    var maxTravelTime by remember { mutableStateOf("") }
    var additionalStops by remember { mutableStateOf("") }
    var accessibilityNeeds by remember { mutableStateOf("") }
    var otherSuggestions by remember { mutableStateOf("") }

    val transports = listOf("Voiture", "Vélo", "Bus", "Tram", "Marche", "Covoiturage", "Autre")
    val frequencies = listOf("Quotidien", "Hebdomadaire", "Mensuel", "Occasionnel")
    val travelTimes = listOf("15 minutes", "30 minutes", "45 minutes", "1 heure", "Plus d'une heure")
    val accessibilityOptions = listOf("Aucun", "Accès PMR", "Ascenseur", "Rampes", "Autre")
    
    var expandedTransport by remember { mutableStateOf(false) }
    var expandedFrequency by remember { mutableStateOf(false) }
    var expandedTravelTime by remember { mutableStateOf(false) }
    var expandedAccessibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Vos besoins futurs",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expandedTransport,
            onExpandedChange = { expandedTransport = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = preferredTransport,
                onValueChange = {},
                readOnly = true,
                label = { Text("Mode de transport préféré") },
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
                            preferredTransport = option
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
                value = desiredFrequency,
                onValueChange = {},
                readOnly = true,
                label = { Text("Fréquence souhaitée des passages") },
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
                            desiredFrequency = option
                            expandedFrequency = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedTravelTime,
            onExpandedChange = { expandedTravelTime = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = maxTravelTime,
                onValueChange = {},
                readOnly = true,
                label = { Text("Temps de trajet maximum acceptable") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTravelTime) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedTravelTime,
                onDismissRequest = { expandedTravelTime = false }
            ) {
                travelTimes.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            maxTravelTime = option
                            expandedTravelTime = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = additionalStops,
            onValueChange = { additionalStops = it },
            label = { Text("Arrêts supplémentaires souhaités") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedAccessibility,
            onExpandedChange = { expandedAccessibility = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = accessibilityNeeds,
                onValueChange = {},
                readOnly = true,
                label = { Text("Besoins d'accessibilité spécifiques") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAccessibility) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedAccessibility,
                onDismissRequest = { expandedAccessibility = false }
            ) {
                accessibilityOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            accessibilityNeeds = option
                            expandedAccessibility = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = otherSuggestions,
            onValueChange = { otherSuggestions = it },
            label = { Text("Autres suggestions ou commentaires") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("thank_you") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Terminer")
        }
    }
} 