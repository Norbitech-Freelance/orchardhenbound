package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.CustomButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.utils.clickableNoRipple
import androidx.compose.material3.MaterialTheme

@Composable
fun GameOverOverlay(
    score: Int,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        FullScreenBackground(
            backgroundRes = R.drawable.bg_overlay,
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.img_game_over),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = 210.dp)
                .width(280.dp)
                .aspectRatio(280.19f / 214f),
            contentScale = ContentScale.Fit
        )

        ScorePlate(
            score = score,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = 450.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = 556.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_game_over_back),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 57.dp, height = 60.dp)
                    .clickableNoRipple { onExit() },
                contentScale = ContentScale.Fit
            )

            CustomButton(
                text = stringResource(R.string.game_play_again),
                onClick = onPlayAgain,
                backgroundRes = R.drawable.btn_play_again_bg,
                width = 220.dp,
                height = 60.dp,
                fillBrush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
        }
    }
}
