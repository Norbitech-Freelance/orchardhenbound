package com.example.orchardhenbound.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun StrokeText(
    text: String,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    fillColor: Color = Color.White,
    fillBrush: Brush? = null,
    strokeColor: Color = STROKE_PRIMARY,
    strokeWidth: Float,
    modifier: Modifier = Modifier
) {
    val baseStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = fontSize,
        lineHeight = fontSize,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center
    )

    val fillStyle =
        if (fillBrush != null) baseStyle.copy(brush = fillBrush)
        else baseStyle.copy(color = fillColor)

    Box(modifier = modifier) {
        Text(
            text = text,
            style = baseStyle.copy(
                color = strokeColor,
                drawStyle = Stroke(width = strokeWidth, join = StrokeJoin.Round)
            )
        )
        Text(
            text = text,
            style = fillStyle
        )
    }
}
