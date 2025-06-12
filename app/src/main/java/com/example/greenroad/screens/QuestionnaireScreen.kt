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
fun QuestionnaireScreen(navController: NavController) {
    var departure by remember { mutableStateOf("") }
    var arrival by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val frequencies = listOf("Quotidien", "Hebdomadaire", "Mensuel", "Occasionnel")
    val times = listOf("Matin (6h-9h)", "Midi (11h-14h)", "Après-midi (14h-17h)", "Soir (17h-20h)")

    var expandedFrequency by remember { mutableStateOf(false) }
    var expandedTime by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Questionnaire de mobilité",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = departure,
            onValueChange = { departure = it },
            label = { Text("Lieu de départ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = arrival,
            onValueChange = { arrival = it },
            label = { Text("Lieu d'arrivée") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedFrequency,
            onExpandedChange = { expandedFrequency = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = frequency,
                onValueChange = {},
                readOnly = true,
                label = { Text("Fréquence") },
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
                            frequency = option
                            expandedFrequency = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedTime,
            onExpandedChange = { expandedTime = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = time,
                onValueChange = {},
                readOnly = true,
                label = { Text("Horaires habituels") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTime) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandedTime,
                onDismissRequest = { expandedTime = false }
            ) {
                times.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            time = option
                            expandedTime = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("summary") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Valider mes trajets")
        }
    }
} 