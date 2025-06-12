package com.example.greenroad.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Couleurs vertes pastel pour le thème clair
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF7CB342), // Vert pastel principal
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCEDC8), // Vert très clair pour les conteneurs
    onPrimaryContainer = Color(0xFF2E7D32), // Vert foncé pour le texte sur conteneur
    secondary = Color(0xFF9CCC65), // Vert pastel secondaire
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8F5E9), // Vert très clair pour les conteneurs secondaires
    onSecondaryContainer = Color(0xFF33691E), // Vert foncé pour le texte sur conteneur secondaire
    tertiary = Color(0xFFAED581), // Vert pastel tertiaire
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF1F8E9), // Vert très très clair pour les conteneurs tertiaires
    onTertiaryContainer = Color(0xFF558B2F), // Vert foncé pour le texte sur conteneur tertiaire
    background = Color(0xFFF5F7F0), // Fond légèrement verdâtre
    onBackground = Color(0xFF1B1B1B),
    surface = Color.White,
    onSurface = Color(0xFF1B1B1B),
    surfaceVariant = Color(0xFFE8F5E9), // Variante de surface en vert très clair
    onSurfaceVariant = Color(0xFF33691E) // Texte sur variante de surface en vert foncé
)

// Couleurs vertes pastel pour le thème sombre
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFAED581), // Vert pastel clair pour le thème sombre
    onPrimary = Color(0xFF1B1B1B),
    primaryContainer = Color(0xFF33691E), // Vert foncé pour les conteneurs
    onPrimaryContainer = Color(0xFFDCEDC8),
    secondary = Color(0xFF9CCC65), // Vert pastel secondaire
    onSecondary = Color(0xFF1B1B1B),
    secondaryContainer = Color(0xFF2E7D32), // Vert foncé pour les conteneurs secondaires
    onSecondaryContainer = Color(0xFFE8F5E9),
    tertiary = Color(0xFF7CB342), // Vert pastel tertiaire
    onTertiary = Color(0xFF1B1B1B),
    tertiaryContainer = Color(0xFF1B5E20), // Vert très foncé pour les conteneurs tertiaires
    onTertiaryContainer = Color(0xFFF1F8E9),
    background = Color(0xFF1B1B1B),
    onBackground = Color(0xFFE8F5E9),
    surface = Color(0xFF1B1B1B),
    onSurface = Color(0xFFE8F5E9),
    surfaceVariant = Color(0xFF2E7D32), // Variante de surface en vert foncé
    onSurfaceVariant = Color(0xFFDCEDC8) // Texte sur variante de surface en vert clair
)

@Composable
fun GreenRoadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Désactivé pour utiliser nos couleurs personnalisées
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}