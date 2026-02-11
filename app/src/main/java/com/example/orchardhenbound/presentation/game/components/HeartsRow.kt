package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R

@Composable
fun HeartsRow(
    lives: Int,
    maxLives: Int = 3,
    totalWidth: Dp = 136.dp,
    heartHeight: Dp = 33.dp,
    modifier: Modifier = Modifier
) {
    val heartSize = heartHeight // 33dp
    val totalGapSpace = totalWidth - (heartSize * maxLives)

    // не даём gap уйти в минус
    val gap = if (maxLives > 1) {
        (totalGapSpace / (maxLives - 1)).coerceAtLeast(0.dp)
    } else {
        0.dp
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxLives) { i ->
            val resId =
                if (i < lives) R.drawable.ic_heart_full else R.drawable.ic_heart_empty

            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier.size(heartSize),
                contentScale = ContentScale.Fit
            )
        }
    }
}
