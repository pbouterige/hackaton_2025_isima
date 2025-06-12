package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.greenroad.components.OpenStreetMap
import org.osmdroid.util.GeoPoint

@Composable
fun LinesMapScreen(navController: NavController) {
    // Points pour la ligne verte (exemple)
    val greenLinePoints = listOf(
        GeoPoint(45.7772, 3.0870), // Clermont-Ferrand centre
        GeoPoint(45.7872, 3.0970), // Point intermédiaire
        GeoPoint(45.7972, 3.1070)  // Point final
    )

    // Points pour la ligne bleue (exemple)
    val blueLinePoints = listOf(
        GeoPoint(45.7772, 3.0870), // Clermont-Ferrand centre
        GeoPoint(45.7672, 3.0770), // Point intermédiaire
        GeoPoint(45.7572, 3.0670)  // Point final
    )

    // Points pour la ligne rouge (exemple)
    val redLinePoints = listOf(
        GeoPoint(45.7772, 3.0870), // Clermont-Ferrand centre
        GeoPoint(45.7772, 3.1070), // Point intermédiaire
        GeoPoint(45.7772, 3.1270)  // Point final
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Carte des lignes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Carte OpenStreetMap
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            OpenStreetMap(
                center = GeoPoint(45.7772, 3.0870), // Clermont-Ferrand
                zoomLevel = 13.0,
                polylines = listOf(
                    Triple(greenLinePoints, "Ligne Verte", 0xFF00FF00.toInt()),
                    Triple(blueLinePoints, "Ligne Bleue", 0xFF0000FF.toInt()),
                    Triple(redLinePoints, "Ligne Rouge", 0xFFFF0000.toInt())
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Légende
        Column {
            LegendItem("Ligne Verte", "Centre - Nord")
            LegendItem("Ligne Bleue", "Centre - Sud")
            LegendItem("Ligne Rouge", "Centre - Est")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}

@Composable
private fun LegendItem(name: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$name : $description",
            style = MaterialTheme.typography.bodyMedium
        )
    }
} 