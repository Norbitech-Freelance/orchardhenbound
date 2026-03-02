package com.example.orchardhenbound.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.ui.theme.AccentGradient
import com.example.orchardhenbound.ui.theme.StrokeColor
import com.example.orchardhenbound.ui.theme.TitleGradient

@Composable
fun TitleOutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 10.sp,
    maxFontSize: TextUnit = 32.sp
) {
    BaseAutoResizedOutlinedText(
        text = text,
        fillBrush = TitleGradient,
        modifier = modifier,
        minFontSize = minFontSize,
        maxFontSize = maxFontSize
    )
}


@Composable
fun AccentOutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 10.sp,
    maxFontSize: TextUnit = 32.sp
) {
    BaseAutoResizedOutlinedText(
        text = text,
        fillBrush = AccentGradient,
        modifier = modifier,
        minFontSize = minFontSize,
        maxFontSize = maxFontSize
    )
}

@Composable
private fun BaseAutoResizedOutlinedText(
    text: String,
    fillBrush: Brush,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 10.sp,
    maxFontSize: TextUnit = 32.sp,
    strokeWidth: Float = 8f
) {

    val fontFamily = MaterialTheme.typography.bodyLarge.fontFamily

    val baseStyle = LocalTextStyle.current.copy(
        fontFamily = fontFamily,
        textAlign = TextAlign.Center
    )

    val outlineStyle = baseStyle.copy(
        color = StrokeColor,
        drawStyle = Stroke(
            miter = 10f,
            width = strokeWidth,
            join = StrokeJoin.Round
        )
    )

    val fillStyle = baseStyle.copy(
        brush = fillBrush
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = text,
            style = outlineStyle,
            autoSize = TextAutoSize.StepBased(minFontSize = minFontSize, maxFontSize = maxFontSize),
            maxLines = 1
        )
        BasicText(
            text = text,
            style = fillStyle,
            autoSize = TextAutoSize.StepBased(minFontSize = minFontSize, maxFontSize = maxFontSize),
            maxLines = 1
        )
    }
}