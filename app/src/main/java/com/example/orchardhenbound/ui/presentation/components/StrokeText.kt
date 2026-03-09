package com.example.orchardhenbound.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.ui.theme.AccentGradient
import com.example.orchardhenbound.ui.theme.BalooFontFamily
import com.example.orchardhenbound.ui.theme.StrokeColor
import com.example.orchardhenbound.ui.theme.TitleGradient

@Composable
fun TitleOutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 10.sp,
    maxFontSize: TextUnit = 28.sp
) {
    BaseAutoResizedOutlinedText(
        text = text,
        fillColor = TitleGradient,
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
        fillColor = AccentGradient,
        modifier = modifier,
        minFontSize = minFontSize,
        maxFontSize = maxFontSize
    )
}

@Composable
private fun BaseAutoResizedOutlinedText(
    text: String,
    fillColor: Color,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 1.sp,
    maxFontSize: TextUnit = 32.sp,
    strokeWidth: Float = 8f
) {


    val baseStyle = LocalTextStyle.current.copy(
        fontFamily = BalooFontFamily,
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
        color = fillColor
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
@Preview(showBackground = true, backgroundColor = 0xFFEEEEEE)
@Composable
fun OutlinedTextComponentsPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleOutlinedText(
                text = "GAME OVER",
                maxFontSize = 40.sp
            )

            AccentOutlinedText(
                text = "90000",
                maxFontSize = 32.sp
            )
        }
    }
}