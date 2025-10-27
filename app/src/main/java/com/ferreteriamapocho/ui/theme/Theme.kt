package com.ferreteriamapocho.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Orange80,
    onPrimary = White,
    secondary = Gray80,
    onSecondary = Black,
    background = White,
    onBackground = Black
)

private val DarkColors = darkColorScheme(
    primary = Orange40,
    onPrimary = Black,
    secondary = Gray40,
    onSecondary = White,
    background = Black,
    onBackground = White
)

@Composable
fun FerreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}
