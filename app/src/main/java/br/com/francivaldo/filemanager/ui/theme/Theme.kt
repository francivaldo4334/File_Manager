package br.com.francivaldo.filemanager.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val ColorPalette = darkColors(
    primary = primary,
    primaryVariant = primary_variant,
    secondary = secondary,
    background = primary_variant,
    surface = surface,
    onPrimary = on_color,
    onBackground = on_color,
    onSurface = on_color,
    onSecondary = on_color
)
@Composable
fun FileManagerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}