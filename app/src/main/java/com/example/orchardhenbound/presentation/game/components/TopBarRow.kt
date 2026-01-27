package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.StrokeText
import com.example.orchardhenbound.ui.theme.BalooFontFamily
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun TopBarRow(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 60.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BackButton(onClick = onClick)


        StrokeText(
            text = buttonText,
            fontFamily = BalooFontFamily,
            fontSize = 40.sp,
            fillBrush = Brush.linearGradient(
                colors = listOf(PLATE_TOP, PLATE_BOTTOM)
            ),
            strokeColor = STROKE_PRIMARY,
            strokeWidth = 1f,
            modifier = Modifier.wrapContentSize()
        )
    }
}
