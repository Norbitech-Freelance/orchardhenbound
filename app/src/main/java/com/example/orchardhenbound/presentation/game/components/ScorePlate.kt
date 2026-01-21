package com.example.orchardhenbound.presentation.game.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun ScorePlate(
    score: Int,
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 40.dp,
    fontSize: TextUnit = 32.sp,
    strokeWidthPx: Float = 3.8f,
    @DrawableRes backgroundRes: Int = R.drawable.bg_score_plate
) {
    val gradient = Brush.verticalGradient(listOf(ACCENT_TOP, ACCENT_BOTTOM))

    val baseStyle = TextStyle(
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
        fontSize = fontSize,
        lineHeight = fontSize,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center
    )

    Box(
        modifier = modifier.size(width, height),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = score.toString(),
            style = baseStyle.copy(
                color = STROKE_PRIMARY,
                drawStyle = Stroke(width = strokeWidthPx, join = StrokeJoin.Round)
            )
        )

        Text(
            text = score.toString(),
            style = baseStyle.copy(brush = gradient)
        )
    }
}
