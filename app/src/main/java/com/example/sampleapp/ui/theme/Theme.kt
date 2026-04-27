package com.example.sampleapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = iOSBlue,
    secondary = iOSIndigo,
    tertiary = iOSPink,
    background = iOSBackgroundDark,
    surface = iOSCardDark,
    onPrimary = iOSTextDark,
    onSecondary = iOSTextDark,
    onTertiary = iOSTextDark,
    onBackground = iOSTextDark,
    onSurface = iOSTextDark,
)

private val LightColorScheme = lightColorScheme(
    primary = iOSBlue,
    secondary = iOSIndigo,
    tertiary = iOSPink,
    background = iOSBackgroundLight,
    surface = iOSCardLight,
    onPrimary = iOSTextLight,
    onSecondary = iOSTextLight,
    onTertiary = iOSTextLight,
    onBackground = iOSTextLight,
    onSurface = iOSTextLight,
)

@Composable
fun IOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
