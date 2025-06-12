package com.example.greenroad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.greenroad.components.OpenStreetMap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.osmdroid.util.GeoPoint

data class TransportProposal(
    val id: String,
    val name: String,
    val description: String,
    val status: String,
    val satisfactionRate: Float,
    val startLocation: GeoPoint,
    val endLocation: GeoPoint,
    val stops: List<GeoPoint>
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProposalsOverviewScreen(navController: NavController) {
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    // Données de démonstration
    val proposals = remember {
        listOf(
            TransportProposal(
                "1",
                "Ligne Verte",
                "Liaison centre-ville - zone industrielle",
                "En cours de validation",
                0.85f,
                GeoPoint(48.8566, 2.3522), // Paris centre
                GeoPoint(48.8566, 2.3522), // Paris centre
                listOf(
                    GeoPoint(48.8566, 2.3522),
                    GeoPoint(48.8566, 2.3522),
                    GeoPoint(48.8566, 2.3522)
                )
            ),
            TransportProposal(
                "2",
                "Ligne Bleue",
                "Liaison gare - université",
                "Proposée",
                0.0f,
                GeoPoint(48.8566, 2.3522),
                GeoPoint(48.8566, 2.3522),
                listOf(
                    GeoPoint(48.8566, 2.3522),
                    GeoPoint(48.8566, 2.3522)
                )
            ),
            TransportProposal(
                "3",
                "Ligne Rouge",
                "Liaison centre commercial - hôpital",
                "Validée",
                0.92f,
                GeoPoint(48.8566, 2.3522),
                GeoPoint(48.8566, 2.3522),
                listOf(
                    GeoPoint(48.8566, 2.3522),
                    GeoPoint(48.8566, 2.3522),
                    GeoPoint(48.8566, 2.3522)
                )
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

        // Carte OpenStreetMap
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            if (locationPermissions.allPermissionsGranted) {
                OpenStreetMap(
                    center = org.osmdroid.util.GeoPoint(45.7772, 3.0870), // Clermont-Ferrand
                    zoomLevel = 13.0,
                    markers = proposals.flatMap { proposal ->
                        listOf(
                            proposal.startLocation to proposal.name,
                            proposal.endLocation to "Terminus"
                        )
                    }
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Localisation requise pour afficher la carte",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { locationPermissions.launchMultiplePermissionRequest() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Autoriser la localisation")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(proposals) { proposal ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("proposal_details/${proposal.id}") }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = proposal.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                            StatusIcon(proposal.status)
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Route,
                                contentDescription = "Trajet",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = proposal.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    contentDescription = "Statut",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = proposal.status,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            
                            if (proposal.satisfactionRate > 0) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Satisfaction",
                                        tint = MaterialTheme.colorScheme.tertiary
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${(proposal.satisfactionRate * 100).toInt()}%",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("feedback") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Donner mon avis")
        }
    }
}

@Composable
fun StatusIcon(status: String) {
    val (icon, color) = when (status) {
        "Validée" -> Pair(Icons.Default.CheckCircle, MaterialTheme.colorScheme.primary)
        "En cours de validation" -> Pair(Icons.Default.HourglassEmpty, MaterialTheme.colorScheme.secondary)
        else -> Pair(Icons.Default.Lightbulb, MaterialTheme.colorScheme.tertiary)
    }
    
    Icon(
        imageVector = icon,
        contentDescription = status,
        tint = color
    )
} 