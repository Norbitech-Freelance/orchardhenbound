package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.presentation.components.StrokeText
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
import com.example.orchardhenbound.ui.theme.BalooFontFamily
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun RecordPlate(
    record: Record,
    dimmed: Boolean
) {
    val bgAlpha = if (dimmed) 0.55f else 1f
    val borderAlpha = if (dimmed) 0.55f else 1f

    val plateBrush = Brush.linearGradient(
        colors = listOf(
            PLATE_TOP.copy(alpha = bgAlpha),
            PLATE_BOTTOM.copy(alpha = bgAlpha)
        )
    )

    // Градиент для текста - используем ACCENT из темы
    val textBrush = Brush.linearGradient(
        colors = listOf(ACCENT_TOP, ACCENT_BOTTOM)
    )

    Box(
        modifier = Modifier
            .width(365.dp)
            .height(60.dp)
            .background(brush = plateBrush, shape = RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = STROKE_PRIMARY.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Дата слева
            StrokeText(
                text = record.date,
                fontFamily = BalooFontFamily,
                fontSize = 32.sp,
                fillBrush = textBrush,
                strokeColor = STROKE_PRIMARY,
                strokeWidth = 1f
            )

            // Счет справа
            StrokeText(
                text = record.score.toString(),
                fontFamily = BalooFontFamily,
                fontSize = 32.sp,
                fillBrush = textBrush,
                strokeColor = STROKE_PRIMARY,
                strokeWidth = 1f
            )
        }
    }
}
