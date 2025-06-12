package com.example.greenroad.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import android.graphics.Color

@Composable
fun OpenStreetMap(
    modifier: Modifier = Modifier,
    center: GeoPoint = GeoPoint(45.7772, 3.0870), // Clermont-Ferrand
    zoomLevel: Double = 13.0,
    markers: List<Pair<GeoPoint, String>> = emptyList(),
    polylines: List<Triple<List<GeoPoint>, String, Int>> = emptyList(),
    onMapReady: (MapView) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Configuration d'Osmdroid
    Configuration.getInstance().userAgentValue = context.packageName

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(zoomLevel)
            controller.setCenter(center)
        }
    }

    // Gestion du cycle de vie
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Ajout des marqueurs
    markers.forEach { (point, title) ->
        val marker = Marker(mapView).apply {
            position = point
            title?.let { setTitle(it) }
        }
        mapView.overlays.add(marker)
    }

    // Ajout des polylines
    polylines.forEach { (points, title, color) ->
        val polyline = Polyline(mapView).apply {
            setPoints(points)
            outlinePaint.color = color
            outlinePaint.strokeWidth = 5f
            title?.let { setTitle(it) }
        }
        mapView.overlays.add(polyline)
    }

    AndroidView(
        factory = { mapView },
        modifier = modifier.fillMaxSize(),
        update = { view ->
            onMapReady(view)
        }
    )
} 