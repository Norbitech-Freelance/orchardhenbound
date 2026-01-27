package com.example.orchardhenbound.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.theme.TEXT_FILL_LIGHT_BOTTOM
import com.example.orchardhenbound.ui.theme.TEXT_FILL_LIGHT_TOP
import com.example.orchardhenbound.utils.extensions.clickableNoRipple

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int = R.drawable.btn_primary_bg,
    height: Dp = 80.dp,
    fillBrush: Brush? = null
) {
    val textStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp)

    val defaultFill = Brush.verticalGradient(
        colors = listOf(TEXT_FILL_LIGHT_TOP, TEXT_FILL_LIGHT_BOTTOM)
    )
    val finalFill = fillBrush ?: defaultFill

    Box(
        modifier = modifier
            .height(height)
            .clickableNoRipple(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = text,
            style = textStyle.copy(brush = finalFill)
        )
    }
}
