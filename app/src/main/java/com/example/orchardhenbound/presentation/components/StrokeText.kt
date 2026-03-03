package com.example.orchardhenbound.presentation.components

import androidx.compose.foundation.layout.Box
<<<<<<< HEAD
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
=======
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
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
