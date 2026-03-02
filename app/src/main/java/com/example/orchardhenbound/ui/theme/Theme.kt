package com.example.orchardhenbound.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    // We map your accent colors into Material colorScheme,
    // so components like ScorePlate that use MaterialTheme.colorScheme work consistently.
    primary = ACCENT_BOTTOM,
    secondary = ACCENT_TOP
)

@Composable
fun OrchardHenboundTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
