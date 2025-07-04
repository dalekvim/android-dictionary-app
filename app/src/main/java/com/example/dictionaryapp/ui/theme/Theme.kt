package com.example.dictionaryapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Some fresh colors, feel free to adjust the hex codes!

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),       // Vibrant Purple
    onPrimary = Color.Black,           // Dark text on primary buttons
    secondary = Color(0xFF03DAC6),     // Teal accent, fresh!
    onSecondary = Color.Black,
    tertiary = Color(0xFFFF80AB),      // Warm pinkish accent
    background = Color(0xFF121212),    // Classic dark background
    onBackground = Color(0xFFE0E0E0),  // Soft off-white text
    surface = Color(0xFF1E1E1E),       // Slightly lighter than background
    onSurface = Color(0xFFE0E0E0),     // Soft text on surface
    error = Color(0xFFCF6679),         // Gentle red for errors
    onError = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),       // Deep Purple with richness
    onPrimary = Color.White,
    secondary = Color(0xFF018786),     // Deep Teal
    onSecondary = Color.White,
    tertiary = Color(0xFFFF4081),      // Bright pink pop
    background = Color(0xFFF2F2F2),    // Soft off-white background
    onBackground = Color(0xFF1C1B1F),  // Dark text for readability
    surface = Color(0xFFFFFFFF),       // White surface for cards etc.
    onSurface = Color(0xFF1C1B1F),     // Dark text on surface
    error = Color(0xFFB00020),
    onError = Color.White
)

@Composable
fun DictionaryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,  // you can spice up typography too if you want!
        content = content
    )
}
