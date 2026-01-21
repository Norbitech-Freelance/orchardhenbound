package com.example.orchardhenbound.presentation.components

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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY
import com.example.orchardhenbound.ui.theme.TEXT_FILL_LIGHT_BOTTOM
import com.example.orchardhenbound.ui.theme.TEXT_FILL_LIGHT_TOP
import com.example.orchardhenbound.utils.clickableNoRipple

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int = R.drawable.btn_primary_bg,
    width: Dp = 260.dp,
    height: Dp = 80.dp,
    fillBrush: Brush? = null
) {
    val textStyle = MaterialTheme.typography.titleLarge

    // Default fill for button text (like in your design)
    val defaultFill = Brush.verticalGradient(
        colors = listOf(TEXT_FILL_LIGHT_TOP, TEXT_FILL_LIGHT_BOTTOM)
    )

    val finalFill = fillBrush ?: defaultFill

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .clickableNoRipple(onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Box {
            // Stroke
            Text(
                text = text,
                style = textStyle.copy(
                    color = STROKE_PRIMARY,
                    drawStyle = Stroke(width = 6f)
                )
            )

            // Fill (gradient, not black)
            Text(
                text = text,
                style = textStyle.copy(brush = finalFill)
            )
        }
    }
}
