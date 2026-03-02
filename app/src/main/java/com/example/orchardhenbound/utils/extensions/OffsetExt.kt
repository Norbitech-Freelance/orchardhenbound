package com.example.orchardhenbound.utils.extensions

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

fun Modifier.offsetPx(xPx: Float, yPx: Float): Modifier =
    offset {
        IntOffset(xPx.roundToInt(), yPx.roundToInt())
    }
